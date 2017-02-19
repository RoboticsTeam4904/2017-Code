package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.GearIO;

public class GearioOuttake extends GearioSet {
	public GearioOuttake() {
		super(GearIO.GearState.OUTTAKE);
	}
}
