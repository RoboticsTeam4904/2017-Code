package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.standard.custom.sensors.NavX;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class FusibleNavX extends NavX implements Fusible<Double> {
	protected double relativeOffset;

	public FusibleNavX(Port port) {
		super(port);
	}

	@Override
	public Double getValue() {
		return getAngle() - relativeOffset;
	}

	@Override
	public boolean trustable() {
		return isMagnetometerCalibrated();
	}

	public void zeroRelativeOffset() {
		setRelativeOffset(getAngle());
	}

	public void setRelativeOffset(double relativeOffset) {
		this.relativeOffset = relativeOffset;
	}

	@Override
	public String getName() {
		return "NavX";
	}
}
