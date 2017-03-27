package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.CalibrateCameraExposure;
import org.usfirst.frc4904.robot.commands.FloorioIntake;
import org.usfirst.frc4904.robot.commands.FloorioOuttake;
import org.usfirst.frc4904.robot.commands.GearClear;
import org.usfirst.frc4904.robot.commands.GearioIntake;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.robot.commands.RampSet;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.standard.commands.OverrideDisable;
import org.usfirst.frc4904.standard.commands.OverrideEnable;
import org.usfirst.frc4904.standard.commands.ThresholdCommand;
import org.usfirst.frc4904.standard.commands.motor.speedmodifiers.SetEnableableModifier;
import org.usfirst.frc4904.standard.humaninput.Operator;

public class DefaultOperator extends Operator {
	public static final double INTAKE_THRESHOLD = 0.5;

	public DefaultOperator() {
		super("DefaultOperator");
	}

	public DefaultOperator(String name) {
		super(name);
	}

	@Override
	public void bindCommands() {
		RobotMap.Component.operatorStick.button1.onlyWhileHeld(new FloorioOuttake());
		RobotMap.Component.operatorStick.button2.onlyWhileHeld(new FloorioIntake());
		RobotMap.Component.operatorStick.button5.whenPressed(new RampSet(GearIO.RampState.RETRACTED));
		RobotMap.Component.operatorStick.button5.whenReleased(new RampSet(GearIO.RampState.EXTENDED));
		RobotMap.Component.operatorStick.button7.onlyWhileHeld(new GearClear());
		RobotMap.Component.operatorStick.button9.whenPressed(new RampSet(GearIO.RampState.RETRACTED));
		RobotMap.Component.operatorStick.button10.whenPressed(new RampSet(GearIO.RampState.EXTENDED));
		new ThresholdCommand<Double>(new GearioIntake(), RobotMap.Component.operatorStick::getY,
			DefaultOperator.INTAKE_THRESHOLD).start();
		new ThresholdCommand<Double>(new GearioOuttake(), RobotMap.Component.operatorStick::getY,
			-DefaultOperator.INTAKE_THRESHOLD, true).start();
		RobotMap.Component.teensyStick.getButton(6).whenPressed(new OverrideEnable(RobotMap.Component.gearIO));
		RobotMap.Component.teensyStick.getButton(6).whenReleased(new OverrideDisable(RobotMap.Component.gearIO));
		RobotMap.Component.teensyStick.getButton(12).whenPressed(new RampSet(GearIO.RampState.RETRACTED));
		RobotMap.Component.teensyStick.getButton(11)
			.whenPressed(new RampSet(GearIO.RampState.EXTENDED));
		RobotMap.Component.teensyStick.getButton(1)
			.whenPressed(new SetEnableableModifier(false, RobotMap.Component.rightWheelAccelerationCap,
				RobotMap.Component.leftWheelAccelerationCap));
		RobotMap.Component.teensyStick.getButton(1).whenReleased(new SetEnableableModifier(true,
			RobotMap.Component.rightWheelAccelerationCap, RobotMap.Component.leftWheelAccelerationCap));
		RobotMap.Component.teensyStick.getButton(10).whenPressed(new CalibrateCameraExposure());
	}
}