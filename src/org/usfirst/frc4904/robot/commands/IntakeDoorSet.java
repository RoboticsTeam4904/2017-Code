package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeDoorSet extends Command {
	protected final DoubleSolenoid.Value targetState;
	
	public IntakeDoorSet(DoubleSolenoid.Value targetState) {
		requires(RobotMap.Component.ballIO);
		this.targetState = targetState;
	}
	
	@Override
	protected void initialize() {
		RobotMap.Component.ballIO.shifter.set(targetState);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
