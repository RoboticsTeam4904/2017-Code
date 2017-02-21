package org.usfirst.frc4904.sovereignty.profiles;


import java.util.LinkedList;

public abstract class SplineGenerator {
	/**
	 * Generates an ordered list of distinct features of the spline. Distinct features are
	 * defined as a sudden change in curvature above the provided curve threshold.
	 * 
	 * @param granularity
	 * @param curveThreshold
	 * @return ordered list of distinct features of the generated spline
	 */
	public LinkedList<SplineSegment> generateFeatureSegments(double granularity, double curveThreshold) {
		LinkedList<SplineSegment> featureSegments = new LinkedList<>();
		double lastPercentage = 0.0;
		// Hopefully the curvature is never non-zero at the initial position of the arc.
		double lastCurve = 0.0;
		SplineSegment lastTrajectorySegment = new SplineSegment(0);
		for (double i = 0; i < 1; i += 1 / granularity) {
			double instantCurve = calcCurvature(i);
			if (Math.abs(lastCurve - instantCurve) > curveThreshold) {
				double curveLen = calcLength(1000, lastPercentage, i + 1 / granularity);
				lastPercentage = i;
				lastCurve = instantCurve;
				lastTrajectorySegment.length = curveLen;
				lastTrajectorySegment.finPercentage = lastPercentage;
				featureSegments.add(lastTrajectorySegment);
				lastTrajectorySegment = new SplineSegment(lastTrajectorySegment.finCurve);
			}
		}
		lastTrajectorySegment.length = calcLength(1000, lastPercentage, 1);
		featureSegments.add(lastTrajectorySegment);
		return featureSegments;
	}

	public double calcLength(double granularity, double a, double b) {
		double arcSum = 0;
		for (double i = a; i < b; i += 1 / granularity) {
			arcSum += calcSpeed(i);
		}
		return arcSum / granularity;
	}

	protected double calcLength(double granularity, double b) {
		return calcLength(granularity, 0.0, b);
	}

	protected double calcAbsoluteLength(double granularity) {
		return calcLength(granularity, 0.0, 1.0);
	}

	protected double calcAbsoluteLength() {
		return calcLength(1000, 0.0, 1.0);
	}

	/**
	 * (X'(s) * Y''(s) - X''(s) * Y'(s)) / v(t)^3
	 * 
	 * @param s
	 *        the position along the spline from [0-1]
	 * @return the curvature of the spline at point s
	 */
	public double calcCurvature(double s) {
		Tuple<Double, Double> vel = calcVel(s);
		Tuple<Double, Double> acc = calcAcc(s);
		double speed = calcSpeed(s);
		// System.out.println(speed);
		return (vel.getX() * acc.getY() - vel.getY() * acc.getX()) / (speed * speed * speed);
	}

	/**
	 * @param s
	 *        the position along the spline from [0-1]
	 * @return the 'velocity' (note that this is not true physical velocity but merely the magnitude of the spline velocity)
	 */
	public double calcSpeed(double s) {
		Tuple<Double, Double> vel = calcVel(s);
		return Math.sqrt(vel.getX() * vel.getX() + vel.getY() * vel.getY());
	}

	/**
	 * Position
	 */
	protected abstract void initializePos();

	/**
	 * @param s
	 *        the position along the spline from [0-1]
	 * @return a tuple of x-pos and y-pos
	 */
	public Tuple<Double, Double> calcPos(double s) {
		return new Tuple<>(PosX(s), PosY(s));
	}

	protected abstract double PosX(double s);

	protected abstract double PosY(double s);

	/**
	 * Velocity is the first derivative of position
	 */
	protected abstract void initializeVel();

	public Tuple<Double, Double> calcVel(double s) {
		return new Tuple<>(VelX(s), VelY(s));
	}

	protected abstract double VelX(double s);

	protected abstract double VelY(double s);

	/**
	 * Acceleration is the second derivative of position
	 */
	protected abstract void initializeAcc();

	public Tuple<Double, Double> calcAcc(double s) {
		return new Tuple<>(AccX(s), AccY(s));
	}

	protected abstract double AccX(double s);

	protected abstract double AccY(double s);
}