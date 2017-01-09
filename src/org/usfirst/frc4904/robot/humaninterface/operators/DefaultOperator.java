package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.standard.humaninput.Operator;

public class DefaultOperator extends Operator {
	
	public static final double JOYSTICK_MIN_THRESH = 0.1;
	
	public DefaultOperator() {
		super("DefaultOperator");
	}
	
	@Override
	public void bindCommands() {}
}