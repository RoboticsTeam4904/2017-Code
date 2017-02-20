package org.usfirst.frc4904.sovereignty.profiles;


public abstract class SplineGenerator {
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