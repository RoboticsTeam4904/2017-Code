package org.usfirst.frc4904.sovereignty.profiles;


public class SplineSegment {
	protected double initCurve;
	protected double finCurve;
	public double initPercentage;
	protected double finPercentage;
	protected double length;

	public SplineSegment(double initCurve, double finCurve, double length) {
		this.initCurve = initCurve;
		this.finCurve = finCurve;
		this.length = length;
	}

	public SplineSegment(double initCurve) {
		this.initCurve = initCurve;
	}

	public SplineSegment(double initCurve, double initPercentage) {
		this.initCurve = initCurve;
		this.initPercentage = initPercentage;
	}

	public double averageCurvature() {
		return (initCurve + finCurve) / 2;
	}
}
