package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ShiftSeeSawIntake extends ShiftSeeSaw {
	public ShiftSeeSawIntake() {
		super(DoubleSolenoid.Value.kForward);
	}
}