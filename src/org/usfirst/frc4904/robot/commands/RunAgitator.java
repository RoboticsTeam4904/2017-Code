package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunAgitator extends Command {
	
	public RunAgitator() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		RobotMap.Component.hopper.agitator.set(0.5);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {
		RobotMap.Component.hopper.agitator.set(0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		RobotMap.Component.hopper.agitator.set(0);
	}
}
