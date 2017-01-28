package org.usfirst.frc4904.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hopper extends Subsystem {
	public DoubleSolenoid seeSawLeft;
	public DoubleSolenoid seeSawRight;
	
	public Hopper(DoubleSolenoid seeSawLeft, DoubleSolenoid seeSawRight) {
		this.seeSawLeft = seeSawLeft;
		this.seeSawRight = seeSawRight;
	}
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
