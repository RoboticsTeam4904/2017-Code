package org.usfirst.frc4904.sovereignty.profiles;


import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class MotionTrajectoryQueue {
	protected final MotionTrajectory trajectory;
	public Deque<Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint>> pointQueue;

	public MotionTrajectoryQueue(MotionTrajectory trajectory) {
		this.trajectory = trajectory;
		pointQueue = new LinkedBlockingDeque<Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint>>();
	}

	public MotionTrajectory getTrajectory() {
		return trajectory;
	}

	protected class MotionTrajectoryBuilder implements Runnable {
		private final MotionTrajectory trajectory;
		private final LinkedBlockingDeque<Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint>> queue;
		private final Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> zeroPoint = new Tuple<>(
			new MotionTrajectoryPoint(0, 0, 0, 0), new MotionTrajectoryPoint(0, 0, 0, 0));
		private int tickNum = 0;

		MotionTrajectoryBuilder(MotionTrajectory trajectory,
			LinkedBlockingDeque<Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint>> queue) {
			this.trajectory = trajectory;
			this.queue = queue;
			queue.add(zeroPoint);
		}

		@Override
		public void run() {
			Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> point = trajectory.calcPoint(tickNum += 1);
			queue.add(point);
		}
	}
}