package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeDoorSet extends Command {
	DoubleSolenoid.Value targetState;
	
	public IntakeDoorSet(DoubleSolenoid.Value targetState) {
		// Use requires() here to declare subsystem dependencies
		requires(RobotMap.Component.ballIO);
		this.targetState = targetState;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		RobotMap.Component.ballIO.shifter.set(targetState);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}
}
