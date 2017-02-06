package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeDoorOuttake extends IntakeDoorSet {
	public IntakeDoorOuttake() {
		super(DoubleSolenoid.Value.kReverse);
	}
}
