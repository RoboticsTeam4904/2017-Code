package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import edu.wpi.first.wpilibj.command.Command;

public class BallioDoorSet extends Command {
	protected final BallIO.DoorState targetState;

	public BallioDoorSet(BallIO.DoorState targetState) {
		requires(RobotMap.Component.ballIO.doorServo);
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
