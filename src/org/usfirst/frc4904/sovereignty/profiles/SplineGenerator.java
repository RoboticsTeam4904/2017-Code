package org.usfirst.frc4904.sovereignty.profiles;


public class SplineGenerator {
	private final double initPosX, initPosY, finPosX, finPosY, initVelX, initVelY, finVelX, finVelY, initAccX, initAccY,
		finAccX, finAccY;
	// TODO: Turn these into individual doubles to remove unnecessary overhead that comes with non-dynamic access of any of the variables.
	private final double[] PosX = new double[4], PosY = new double[4];
	private final double[] VelX = new double[3], VelY = new double[3];
	private final double[] AccX = new double[2], AccY = new double[2];

	SplineGenerator(double initPosX, double initPosY, double finPosX, double finPosY,
		double initVelX, double initVelY, double finVelX, double finVelY,
		double initAccX, double initAccY, double finAccX, double finAccY) {
		this.initPosX = initPosX;
		this.initPosY = initPosY;
		this.finPosX = finPosX;
		this.finPosY = finPosY;
		this.initVelX = initVelX;
		this.initVelY = initVelY;
		this.finVelX = finVelX;
		this.finVelY = finVelY;
		this.initAccX = initAccX;
		this.initAccY = initAccY;
		this.finAccX = finAccX;
		this.finAccY = finAccY;
		initializePos();
		initializeVel();
		initializeAcc();
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
	 * @param s
	 *        the position along the spline from [0-1]
	 * @return a tuple of x-pos and y-pos
	 */
	public Tuple<Double, Double> calcPos(double s) {
		return new Tuple<>(PosX(s), PosY(s));
	}

	/**
	 * Position
	 */
	private void initializePos() {
		/* a */PosX[0] = -6 * initPosX + 6 * finPosX - 3 * initVelX - 3 * finVelX - (initAccX / 2.0) + (finAccX / 2.0);
		/* b */PosX[1] = 15 * initPosX - 15 * finPosX + 8 * initVelX + 7 * finVelX + (3.0 / 2.0) * initAccX - finAccX;
		/* c */PosX[2] = -10 * initPosX + 10 * finPosX - 6 * initVelX - 4 * finVelX - (3.0 / 2.0) * initAccX + (finAccX / 2.0);
		/* d */PosX[3] = (initAccX / 2.0);
		/* e */PosX[4] = initVelX;
		/* f */PosX[5] = initPosX;
		/* a */PosY[0] = -6 * initPosY + 6 * finPosY - 3 * initVelY - 3 * finVelY - (initAccY / 2.0) + (finAccY / 2.0);
		/* b */PosY[1] = 15 * initPosY - 15 * finPosY + 8 * initVelY + 7 * finVelY + (3.0 / 2.0) * initAccY - finAccY;
		/* c */PosY[2] = -10 * initPosY + 10 * finPosY - 6 * initVelY - 4 * finVelY - (3.0 / 2.0) * initAccY + (finAccY / 2.0);
		/* d */PosY[3] = (initAccY / 2.0);
		/* e */PosY[4] = initVelY;
		/* f */PosY[5] = initPosY;
	}

	private double PosX(double s) {
		return PosX[0] * s * s * s * s * s
			+ PosX[1] * s * s * s * s
			+ PosX[2] * s * s * s
			+ PosX[3] * s * s
			+ PosX[4] * s
			+ PosX[5];
	}

	private double PosY(double s) {
		return PosY[0] * s * s * s * s * s
			+ PosY[1] * s * s * s * s
			+ PosY[2] * s * s * s
			+ PosY[3] * s * s
			+ PosY[4] * s
			+ PosY[5];
	}

	/**
	 * Velocity is the first derivative of position
	 */
	private void initializeVel() {
		VelX[0] = PosX[0] * 5;
		VelX[1] = PosX[1] * 4;
		VelX[2] = PosX[2] * 3;
		VelX[3] = PosX[3] * 2;
		VelX[4] = PosX[4];
		VelY[0] = PosY[0] * 5;
		VelY[1] = PosY[1] * 4;
		VelY[2] = PosY[2] * 3;
		VelY[3] = PosY[3] * 2;
		VelY[4] = PosY[4];
	}

	public Tuple<Double, Double> calcVel(double s) {
		return new Tuple<>(VelX(s), VelY(s));
	}

	private double VelX(double s) {
		return VelX[0] * s * s * s * s
			+ VelX[1] * s * s * s
			+ VelX[2] * s * s
			+ VelX[3] * s
			+ VelX[4];
	}

	private double VelY(double s) {
		return VelY[0] * s * s * s * s
			+ VelY[1] * s * s * s
			+ VelY[2] * s * s
			+ VelY[3] * s
			+ VelY[4];
	}

	/**
	 * Acceleration is the second derivative of position
	 */
	private void initializeAcc() {
		AccX[0] = VelX[0] * 4;
		AccX[1] = VelX[1] * 3;
		AccX[2] = VelX[2] * 2;
		AccX[3] = VelX[3];
		AccY[0] = VelY[0] * 4;
		AccY[1] = VelY[1] * 3;
		AccY[2] = VelY[2] * 2;
		AccY[3] = VelY[3];
	}

	public Tuple<Double, Double> calcAcc(double s) {
		return new Tuple<>(AccX(s), AccY(s));
	}

	private double AccX(double s) {
		return AccX[0] * s * s * s
			+ AccX[1] * s * s
			+ AccX[2] * s
			+ AccX[3];
	}

	private double AccY(double s) {
		return AccY[0] * s * s * s
			+ AccY[1] * s * s
			+ AccY[2] * s
			+ AccY[3];
	}
}