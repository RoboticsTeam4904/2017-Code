package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.BallIO;

public class BallioDoorSetIntake extends BallioDoorSet {
	public BallioDoorSetIntake() {
		super(BallIO.DoorState.INTAKE);
	}
}
