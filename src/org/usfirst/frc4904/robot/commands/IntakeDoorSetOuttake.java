package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeDoorSetOuttake extends IntakeDoorSet {
	public IntakeDoorSetOuttake() {
		super(DoubleSolenoid.Value.kReverse);
	}
}
