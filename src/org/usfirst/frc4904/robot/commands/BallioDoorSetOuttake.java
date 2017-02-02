package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.BallIO;

public class BallioDoorSetOuttake extends BallioDoorSet {
	public BallioDoorSetOuttake() {
		super(BallIO.DoorState.INTAKE);
	}
}
