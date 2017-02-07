package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.GearIO;

public class GearIntake extends GearSet {
	public GearIntake() {
		super(GearIO.GearState.INTAKE);
	}
}
