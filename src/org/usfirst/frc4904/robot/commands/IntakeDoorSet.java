package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeDoorSet extends Command {
	protected final Boolean isUp;
	
	public IntakeDoorSet(Boolean targetState) {
		requires(RobotMap.Component.ballIO);
		isUp = targetState;
	}
	
	@Override
	protected void initialize() {
		if (isUp) {
			RobotMap.Component.ballIO.shifter.setAngle(BallIO.intakeAngle);
		} else {
			RobotMap.Component.ballIO.shifter.setAngle(BallIO.outtakeAngle);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
	}
}
