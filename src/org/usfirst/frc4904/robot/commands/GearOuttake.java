package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.GearIO;

public class GearOuttake extends GearSet {
	public GearOuttake() {
		super(GearIO.GearState.OUTTAKE);
	}
}
