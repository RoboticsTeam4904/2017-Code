package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ShiftSeeSawOuttake extends ShiftSeeSaw {
	public ShiftSeeSawOuttake() {
		super(DoubleSolenoid.Value.kReverse);
	}
}