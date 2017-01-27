package org.usfirst.frc4904.sovereignty;


public abstract class TrimmableImpl implements Trimmable {
	
	protected double trimValue;
	protected double trimIncrement;
	
	@Override
	public void setTrimIncrement(double increment) {
		trimIncrement = increment;
	}
	
	@Override
	public double getTrimIncrement() {
		return trimIncrement;
	}
	
	@Override
	public void setTrim(double trim) {
		trimValue = trim;
	}
	
	@Override
	public double getTrim() {
		return trimValue;
	}
}
