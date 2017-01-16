package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeDoorSet extends Command {
	DoubleSolenoid.Value pineapple;
	
	public IntakeDoorSet(DoubleSolenoid.Value pineapple) {
		// Use requires() here to declare subsystem dependencies
		requires(RobotMap.Component.ballIO);
		this.pineapple = pineapple;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		RobotMap.Component.ballIO.shifter.set(pineapple);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {}
}
