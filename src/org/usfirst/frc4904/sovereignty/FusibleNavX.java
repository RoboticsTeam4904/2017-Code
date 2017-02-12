package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.standard.custom.sensors.NavX;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class FusibleNavX extends NavX implements Fusible<Double> {
	public static enum NavxMode {
		STANDARD, ALIGNMENT;
	}
	protected double relativeOffset = 0;
	protected double targetAngle = 0;
	protected NavxMode mode = NavxMode.STANDARD;

	public FusibleNavX(Port port) {
		super(port);
	}

	@Override
	public Double getValue() {
		switch (mode) {
			default:
				throw new IllegalStateException("NavX must have a NavXMode state");
			case STANDARD:
				return getAngle();
			case ALIGNMENT:
				return targetAngle - (getAngle() - relativeOffset);
		}
	}

	@Override
	public boolean trustable() {
		return isConnected();
	}

	public void setMode(NavxMode mode) {
		this.mode = mode;
	}

	public void setModeAlignment() {
		setMode(NavxMode.ALIGNMENT);
		zeroRelativeOffset();
		resetTargetAngle();
	}

	public void setModeStandard() {
		setMode(NavxMode.STANDARD);
		resetRelativeOffset();
		resetTargetAngle();
	}

	public void zeroRelativeOffset() {
		setRelativeOffset(getAngle());
	}

	public void setRelativeOffset(double relativeOffset) {
		this.relativeOffset = relativeOffset;
	}

	public void resetRelativeOffset() {
		relativeOffset = 0;
	}

	public void setTargetAngle(double targetAngle) {
		this.targetAngle = targetAngle;
	}

	public void resetTargetAngle() {
		targetAngle = 0;
	}
}
