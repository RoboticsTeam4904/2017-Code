package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.GearIO;

public class GearioIntake extends GearioSet {
	public GearioIntake() {
		super(GearIO.GearState.INTAKE);
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (new SetRampState(null).isNotOverridden()) {
			new SetRampState(GearIO.RampState.EXTENDED).start();
		}
	}
}
