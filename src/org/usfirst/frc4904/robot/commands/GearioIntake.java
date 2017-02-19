package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;

public class GearioIntake extends GearioSet {
	public GearioIntake() {
		super(GearIO.GearState.INTAKE);
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (GearIO.rampOverride == false) {
			RobotMap.Component.gearIO.setRampState(GearIO.RampState.EXTENDED);
		}
	}
}
