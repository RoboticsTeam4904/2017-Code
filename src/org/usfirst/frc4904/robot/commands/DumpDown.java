package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DumpDown extends Command {
	
	public DumpDown() {
		requires(RobotMap.Component.ballDumper);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		RobotMap.Component.ballDumper.stopSpinOuttake();
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		RobotMap.Component.ballDumper.spinElevator(-1.0);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {}
}
