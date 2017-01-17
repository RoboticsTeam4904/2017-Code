package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeDoorSetIntake extends IntakeDoorSet {
	public IntakeDoorSetIntake() {
		super(DoubleSolenoid.Value.kForward);
	}
}
