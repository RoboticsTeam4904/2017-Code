package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.BallioFloorClear;
import org.usfirst.frc4904.robot.commands.BallioIntake;
import org.usfirst.frc4904.robot.commands.BallioOuttake;

public class BillyOperator extends DefaultOperator {
	public BillyOperator() {
		super("BillyOperator");
	}

	@Override
	public void bindCommands() {
		RobotMap.Component.operatorStick.button7.onlyWhileHeld(new BallioFloorClear());
		RobotMap.Component.operatorStick.button8.onlyWhileHeld(new BallioOuttake());
		RobotMap.Component.operatorStick.button10.onlyWhileHeld(new BallioIntake());
	}
}