package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeDoorIntake extends IntakeDoorSet {
	public IntakeDoorIntake() {
		super(DoubleSolenoid.Value.kForward);
	}
}
