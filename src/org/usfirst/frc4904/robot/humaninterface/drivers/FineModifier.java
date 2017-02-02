package org.usfirst.frc4904.robot.humaninterface.drivers;

import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifier;

public class FineModifier implements SpeedModifier {

	private boolean fineEnabled = false;

	@Override
	public double modify(double speed) {
		if (fineEnabled) {
			return speed / 3;
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