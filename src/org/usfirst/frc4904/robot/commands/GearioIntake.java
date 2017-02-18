package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.GearIO;

public class GearioIntake extends GearSet {
	public GearioIntake() {
		super(GearIO.GearState.INTAKE);
	}
}
