package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.vision.AligningCameraGRIP;
import org.usfirst.frc4904.sovereignty.TrimCommand;
import org.usfirst.frc4904.sovereignty.TrimCommand.TrimDirection;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.commands.chassis.ChassisShift;
import org.usfirst.frc4904.standard.humaninput.Driver;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;

public class NathanGain extends Driver {
	public static final double SPEED_GAIN = 1;
	public static final double TURN_GAIN = 1;
	public static final double SPEED_EXP = 2;
	public static final double TURN_EXP = 2;
	public static final double Y_SPEED_SCALE = 1;
	public static final double TURN_SPEED_SCALE = 1;
	private final GearAlign gearAlign = new GearAlign(new AligningCameraGRIP());

	public NathanGain() {
		super("NathanGain");
	}

	protected double scaleGain(double input, double gain, double exp) {
		return Math.pow(input, exp) * gain * Math.signum(input);
	}

	@Override
	public void bindCommands() {
		RobotMap.Component.xbox.a
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.DOWN));
		RobotMap.Component.xbox.b
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.UP));
		RobotMap.Component.xbox.dPad.left
			.whenPressed(new TrimCommand(gearAlign, TrimDirection.LEFT));
		RobotMap.Component.xbox.dPad.right
			.whenPressed(new TrimCommand(gearAlign, TrimDirection.RIGHT));
	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		double rawSpeed = RobotMap.Component.xbox.rt.getX()
			- RobotMap.Component.xbox.lt.getX();
		double speed = scaleGain(rawSpeed, NathanGain.SPEED_GAIN, NathanGain.SPEED_EXP) * NathanGain.Y_SPEED_SCALE;
		return speed;
	}

	@Override
	public double getTurnSpeed() {
		double rawTurnSpeed = RobotMap.Component.xbox.leftStick.getX();
		double turnSpeed = scaleGain(rawTurnSpeed, NathanGain.TURN_GAIN, NathanGain.TURN_EXP) * NathanGain.TURN_SPEED_SCALE;
		return turnSpeed;
	}
}
