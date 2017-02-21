package org.usfirst.frc4904.sovereignty.profiles;


import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Notifier;

public class CANTalonMotionTrajectoryExecutor {
	// Callback for the cancel method.
	private static interface Callback {
		public void cancel();
	}
	protected MotionTrajectoryQueue queue;
	protected final MotionTrajectory trajectory;
	protected final CANTalon motorLeft, motorRight;
	protected final TalonControlMode originalModeLeft, originalModeRight;
	protected CANTalonMotionTrajectoryPointMessenger pointMessenger;
	protected CANTalonMotionTrajectoryStatusManager statusManager;
	protected CANTalonMotionTrajectoryBufferUpdater bufferUpdater;
	protected Notifier bufferUpdaterNotifier;
	private final Thread pointMessengerThread;
	private final Thread statusManagerThread;

	/**
	 * Executes a motion trajectory on CANTalon motors.
	 * 
	 * @param motorLeft
	 * @param motorRight
	 * @param queue
	 *        the queue from which we fetch motion trajectory points during the motion trajectory.
	 */
	public CANTalonMotionTrajectoryExecutor(CANTalon motorLeft, CANTalon motorRight, MotionTrajectoryQueue queue) {
		this.motorLeft = motorLeft;
		this.motorRight = motorRight;
		originalModeLeft = motorLeft.getControlMode();
		originalModeRight = motorLeft.getControlMode();
		this.queue = queue;
		trajectory = queue.getTrajectory();
		pointMessenger = new CANTalonMotionTrajectoryPointMessenger(this, queue, motorLeft, motorRight);
		pointMessengerThread = new Thread(pointMessenger);
		statusManager = new CANTalonMotionTrajectoryStatusManager(motorLeft, motorRight, () -> cancel());
		statusManagerThread = new Thread(statusManager);
		bufferUpdater = new CANTalonMotionTrajectoryBufferUpdater(motorLeft, motorRight);
		bufferUpdaterNotifier = new Notifier(bufferUpdater);
		this.motorLeft.changeMotionControlFramePeriod((int) trajectory.tickTime / 2);
		this.motorRight.changeMotionControlFramePeriod((int) trajectory.tickTime / 2);
	}

	public CANTalon.TrajectoryPoint standardToTalonPoint(MotionTrajectoryPoint standardPoint) {
		CANTalon.TrajectoryPoint canPoint = new CANTalon.TrajectoryPoint();
		canPoint.position = standardPoint.pos;
		canPoint.velocity = standardPoint.vel;
		canPoint.timeDurMs = (int) trajectory.tickTime;
		canPoint.profileSlotSelect = 0;
		canPoint.velocityOnly = false;
		canPoint.zeroPos = standardPoint.tick == 0;
		canPoint.isLastPoint = standardPoint.tick == trajectory.tickTotal;
		return canPoint;
	}

	/**
	 * Starts and initializes the motors and the necessary threads.
	 */
	public void start() {
		motorLeft.changeControlMode(TalonControlMode.MotionProfile);
		motorRight.changeControlMode(TalonControlMode.MotionProfile);
		motorLeft.clearMotionProfileTrajectories();
		motorRight.clearMotionProfileTrajectories();
		motorLeft.set(CANTalon.SetValueMotionProfile.Disable.value);
		motorRight.set(CANTalon.SetValueMotionProfile.Disable.value);
		statusManagerThread.start();
		pointMessengerThread.start();
		bufferUpdaterNotifier.startPeriodic((trajectory.tickTime / 2) / 1000);
	}

	/**
	 * Cleanup threads and motor settings when the trajectory is stopped for
	 * any reason.
	 */
	public void cancel() {
		pointMessengerThread.interrupt();
		statusManagerThread.interrupt();
		bufferUpdaterNotifier.stop();
		// Be a good citizen: Clean up after yourself
		motorLeft.changeControlMode(originalModeLeft);
		motorRight.changeControlMode(originalModeRight);
		motorLeft.clearMotionProfileTrajectories();
		motorRight.clearMotionProfileTrajectories();
	}

	private static class CANTalonMotionTrajectoryStatusManager implements Runnable {
		private final CANTalon motorLeft, motorRight;
		private LocalState localState = LocalState.LOADING;
		private final Callback cancelCallback;

		private enum LocalState {
			LOADING, ACTIVE;
		}

		/**
		 * Watches and manages the status of the left/right motors to ensure the
		 * motors act as intended.
		 * 
		 * @param motorLeft
		 * @param motorRight
		 * @param cancelCallback
		 *        ensures that the proper cleanup is run in the event that the
		 *        control mode is switched unexpectedly.
		 */
		CANTalonMotionTrajectoryStatusManager(CANTalon motorLeft, CANTalon motorRight, Callback cancelCallback) {
			this.motorLeft = motorLeft;
			this.motorRight = motorRight;
			this.cancelCallback = cancelCallback;
		}

		@Override
		public void run() {
			CANTalon.MotionProfileStatus statusLeft = new CANTalon.MotionProfileStatus();
			CANTalon.MotionProfileStatus statusRight = new CANTalon.MotionProfileStatus();
			motorLeft.getMotionProfileStatus(statusLeft);
			motorRight.getMotionProfileStatus(statusRight);
			if (motorLeft.getControlMode() != TalonControlMode.MotionProfile
				|| motorRight.getControlMode() != TalonControlMode.MotionProfile) {
				cancelCallback.cancel();
			}
			switch (localState) {
				case LOADING:
					if (statusLeft.btmBufferCnt > CANTalonMotionTrajectoryPointMessenger.TALON_MIN_POINTS
						&& statusRight.btmBufferCnt > CANTalonMotionTrajectoryPointMessenger.TALON_MIN_POINTS) {
						motorLeft.set(CANTalon.SetValueMotionProfile.Enable.value);
						motorRight.set(CANTalon.SetValueMotionProfile.Enable.value);
						localState = LocalState.ACTIVE;
					}
					break;
				case ACTIVE:
					if (statusLeft.activePointValid && statusRight.activePointValid && statusLeft.activePoint.isLastPoint
						&& statusRight.activePoint.isLastPoint) {
						motorLeft.set(CANTalon.SetValueMotionProfile.Hold.value);
						motorRight.set(CANTalon.SetValueMotionProfile.Hold.value);
						localState = LocalState.LOADING;
					}
			}
		}
	}

	private static class CANTalonMotionTrajectoryBufferUpdater implements Runnable {
		private final CANTalon motorLeft, motorRight;

		/**
		 * Tells the CANTalons to process the buffer. This should be run periodically,
		 * at a rate faster than the tick time to ensure that the motors don't run out
		 * of points.
		 * 
		 * @param motorLeft
		 * @param motorRight
		 */
		CANTalonMotionTrajectoryBufferUpdater(CANTalon motorLeft, CANTalon motorRight) {
			this.motorLeft = motorLeft;
			this.motorRight = motorRight;
		}

		@Override
		public void run() {
			motorLeft.processMotionProfileBuffer();
			motorRight.processMotionProfileBuffer();
		}
	}

	private static class CANTalonMotionTrajectoryPointMessenger implements Runnable {
		private final CANTalonMotionTrajectoryExecutor executor;
		private final MotionTrajectoryQueue queue;
		private final CANTalon motorLeft, motorRight;
		static final int TALON_MIN_POINTS = 5;

		/**
		 * Manages pushing the points to the CANTalons as soon as it gets a point from
		 * the trajectory queue.
		 * 
		 * @param executor
		 *        provides access to the standardToTalonPoint method
		 * @param queue
		 *        the wrapper around the queue that the MotionTrajectoryPoints are added to.
		 * @param motorLeft
		 * @param motorRight
		 */
		CANTalonMotionTrajectoryPointMessenger(CANTalonMotionTrajectoryExecutor executor, MotionTrajectoryQueue queue,
			CANTalon motorLeft, CANTalon motorRight) {
			this.executor = executor;
			this.queue = queue;
			this.motorLeft = motorLeft;
			this.motorRight = motorRight;
		}

		@Override
		public void run() {
			Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> pointPair = queue.pointQueue.pop();
			motorLeft.pushMotionProfileTrajectory(executor.standardToTalonPoint(pointPair.getX()));
			motorRight.pushMotionProfileTrajectory(executor.standardToTalonPoint(pointPair.getY()));
		}
	}
}
