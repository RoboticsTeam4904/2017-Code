package org.usfirst.frc4904.sovereignty.profiles;


import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Notifier;

public class CANTalonMotionTrajectoryExecutor {
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

	public void cancel() {
		pointMessengerThread.interrupt();
		statusManager.cancel();
		bufferUpdaterNotifier.stop();
		// Be a good citizen: Clean up after yourself
		motorLeft.changeControlMode(originalModeLeft);
		motorRight.changeControlMode(originalModeRight);
		motorLeft.clearMotionProfileTrajectories();
		motorRight.clearMotionProfileTrajectories();
	}

	private static class CANTalonMotionTrajectoryStatusManager implements Runnable {
		private volatile boolean interrupted = false;
		private final CANTalon motorLeft, motorRight;
		private LocalState localState = LocalState.LOADING;
		private final Callback cancelCallback;

		private enum LocalState {
			LOADING, ACTIVE;
		}

		CANTalonMotionTrajectoryStatusManager(CANTalon motorLeft, CANTalon motorRight, Callback cancelCallback) {
			this.motorLeft = motorLeft;
			this.motorRight = motorRight;
			this.cancelCallback = cancelCallback;
		}

		@Override
		public void run() {
			while (!interrupted) {
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
			interrupted = false;
		}

		public void cancel() {
			interrupted = true;
		}
	}

	private static class CANTalonMotionTrajectoryBufferUpdater implements Runnable {
		private final CANTalon motorLeft, motorRight;

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
