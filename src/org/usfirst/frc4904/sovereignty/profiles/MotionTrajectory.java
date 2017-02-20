package org.usfirst.frc4904.sovereignty.profiles;


import org.usfirst.frc4904.sovereignty.profiles.WheelTrajectory.Wheel;

public class MotionTrajectory {
	protected final SplineGenerator splineGenerator;
	protected final double plantWidth;
	protected final double tickTime, tickTotal;
	protected final WheelTrajectory leftWheel, rightWheel;

	public MotionTrajectory(SplineGenerator splineGenerator, double plantWidth, double tickTime, double tickTotal) {
		this.splineGenerator = splineGenerator;
		this.plantWidth = plantWidth;
		this.tickTime = tickTime;
		this.tickTotal = tickTotal;
		leftWheel = new WheelTrajectory(this, Wheel.LEFT, tickTotal);
		rightWheel = new WheelTrajectory(this, Wheel.RIGHT, tickTotal);
	}

	// public Tuple<Integer, MotionTrajectorySegment> generateSegments() {
	// for(int i = 0; i < granularity; i++) {
	//
	// }
	// Tuple<Integer, MotionTrajectorySegment> trajectorySegment;
	// return
	// }
	public double fromTickToS(double t) {
		return t / tickTotal;
	}

	public Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> calcPoint(int t,
		Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> lastPoint) {
		double s = fromTickToS(t);
		MotionTrajectoryPoint leftPoint = new MotionTrajectoryPoint(t, leftWheel.calcPos(s, lastPoint.getX()),
			leftWheel.calcSpeed(s), leftWheel.calcAcc(s, lastPoint.getX()));
		MotionTrajectoryPoint rightPoint = new MotionTrajectoryPoint(t, rightWheel.calcPos(s, lastPoint.getY()),
			rightWheel.calcSpeed(s), rightWheel.calcAcc(s, lastPoint.getY()));
		return new Tuple<>(leftPoint, rightPoint);
	}

	public double calcSpeed(double s) {
		return splineGenerator.calcSpeed(s);
	}

	public double calcAngularVel(double s) {
		return calcTurning(s) * plantWidth; // = theta/second * circumference/2pi = distance / second
	}

	protected double calcTurning(double s) {
		return splineGenerator.calcCurvature(s) * calcSpeed(s);
	}
}
