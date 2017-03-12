package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.BallioCycle;
import org.usfirst.frc4904.robot.commands.BallioFloorClear;
import org.usfirst.frc4904.robot.commands.BallioIntake;
import org.usfirst.frc4904.robot.commands.BallioOuttake;
import org.usfirst.frc4904.robot.commands.CalibrateCameraExposure;
import org.usfirst.frc4904.robot.commands.FlywheelSpinup;
import org.usfirst.frc4904.robot.commands.GearioIntake;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.robot.commands.HopperSetBallio;
import org.usfirst.frc4904.robot.commands.HopperSetShooter;
import org.usfirst.frc4904.robot.commands.SetOverride;
import org.usfirst.frc4904.robot.commands.SetRampState;
import org.usfirst.frc4904.robot.commands.Shoot;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.standard.commands.ThresholdCommand;
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
		RobotMap.Component.operatorStick.button1.onlyWhileHeld(new Shoot());
		RobotMap.Component.operatorStick.button2.onlyWhileHeld(new FlywheelSpinup());
		RobotMap.Component.operatorStick.button3.onlyWhileHeld(new BallioIntake());
		RobotMap.Component.operatorStick.button4.onlyWhileHeld(new BallioOuttake());
		RobotMap.Component.operatorStick.button5.whenPressed(new SetRampState(GearIO.RampState.RETRACTED));
		RobotMap.Component.operatorStick.button5.whenReleased(new SetRampState(GearIO.RampState.EXTENDED));
		RobotMap.Component.operatorStick.button6.onlyWhileHeld(new BallioFloorClear());
		RobotMap.Component.operatorStick.button7.onlyWhileHeld(new BallioFloorClear());
		RobotMap.Component.operatorStick.button8.onlyWhileHeld(new BallioCycle());
		RobotMap.Component.operatorStick.button9.whenPressed(new SetRampState(GearIO.RampState.RETRACTED));
		RobotMap.Component.operatorStick.button10.whenPressed(new SetRampState(GearIO.RampState.EXTENDED));
		RobotMap.Component.operatorStick.button11.whenPressed(new HopperSetShooter());
		RobotMap.Component.operatorStick.button12.whenPressed(new HopperSetBallio());
		new ThresholdCommand<Double>(new GearioIntake(), RobotMap.Component.operatorStick::getY,
			DefaultOperator.INTAKE_THRESHOLD).start();
		new ThresholdCommand<Double>(new GearioOuttake(), RobotMap.Component.operatorStick::getY,
			-DefaultOperator.INTAKE_THRESHOLD, true).start();
		RobotMap.Component.gearIO.setRampState(GearIO.RampState.EXTENDED);
		RobotMap.Component.teensyStick.getButton(11).whenPressed(new SetOverride(true, RobotMap.Component.gearIO));
		RobotMap.Component.teensyStick.getButton(11).whenReleased(new SetOverride(false, RobotMap.Component.gearIO));
		RobotMap.Component.teensyStick.getButton(12)
			.whenPressed(new SetRampState(GearIO.RampState.EXTENDED));
		RobotMap.Component.teensyStick.getButton(13).whenPressed(new SetRampState(GearIO.RampState.RETRACTED));
		RobotMap.Component.teensyStick.getButton(15).whenPressed(new CalibrateCameraExposure());
	}
}