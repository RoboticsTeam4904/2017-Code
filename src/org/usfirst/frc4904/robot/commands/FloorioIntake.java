package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.FloorIO;

public class FloorioIntake extends FloorioSet {
	public FloorioIntake() {
		super(FloorIO.FloorState.INTAKE);
	}
}
