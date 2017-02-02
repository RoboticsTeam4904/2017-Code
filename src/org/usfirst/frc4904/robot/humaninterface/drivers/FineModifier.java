package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifier;

public class FineModifier implements SpeedModifier {
	protected boolean fineEnabled = false;
	protected final double scale;

	public FineModifier(double scale) {
		this.scale = scale;
	}

	@Override
	public double modify(double speed) {
		if (fineEnabled) {
			return speed / scale;
		}
		return speed;
	}

	public void setFineControl(boolean fine) {
		fineEnabled = fine;
	}

	public boolean getFineControl() {
		return fineEnabled;
	}
}