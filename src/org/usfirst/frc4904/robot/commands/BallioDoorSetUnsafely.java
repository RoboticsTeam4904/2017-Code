package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallioDoorSetUnsafely extends CommandGroup {
	protected BallIO.DoorState targetState;

	public BallioDoorSetUnsafely(BallIO.DoorState targetState) {
		requires(RobotMap.Component.ballIO);
		this.targetState = targetState;
	}

	@Override
	protected void initialize() {
		RobotMap.Component.ballIO.setDoorState(targetState);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}