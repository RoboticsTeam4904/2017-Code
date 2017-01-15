package org.usfirst.frc4904.robot.vision;


import org.usfirst.frc4904.standard.util.Tuple;

public class FrameValue extends Tuple<Double, Boolean> {
	
	public FrameValue(Double x, Boolean y) {
		super(x, y);
	}
	
	public Double getValue() {
		return x;
	}
	
	public Boolean getVisible() {
		return y;
	}
}
