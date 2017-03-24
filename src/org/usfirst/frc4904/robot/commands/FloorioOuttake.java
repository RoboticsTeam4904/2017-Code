package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.FloorIO;

public class FloorioOuttake extends FloorioSet {
	public FloorioOuttake() {
		super(FloorIO.FloorState.OUTTAKE);
	}
}
