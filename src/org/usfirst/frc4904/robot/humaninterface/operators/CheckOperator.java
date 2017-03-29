package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.ChangeTestingCounter;
import org.usfirst.frc4904.standard.humaninput.Operator;

public class CheckOperator extends Operator {
	public static final double INTAKE_THRESHOLD = 0.5;
	public static int counter = 0;

	public CheckOperator() {
		super("DefaultOperator");
	}

	@Override
	public void bindCommands() {
		RobotMap.Component.operatorStick.button5.whenReleased(new ChangeTestingCounter(-1));
		RobotMap.Component.operatorStick.button6.whenReleased(new ChangeTestingCounter(1));
	}
}