package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.ChassisControllerGroup;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.autonomous.strategies.WiggleApproach;
import org.usfirst.frc4904.robot.commands.Climb;
import org.usfirst.frc4904.robot.humaninterface.HumanInterfaceConfig;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.commands.chassis.ChassisShift;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurnAbsolute;
import org.usfirst.frc4904.standard.humaninput.Driver;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import edu.wpi.first.wpilibj.command.Command;

public class NathanGain extends Driver {
	public static final double SPEED_GAIN = 1;
	public static final double SPEED_EXP = 2;
	public static final double TURN_GAIN = 1;
	public static final double TURN_EXP = 2;
	public static final double CLIMB_GAIN = 1;
	public static final double CLIMB_EXP = 2;
	public static final double Y_SPEED_SCALE = 1;
	public static final double TURN_SPEED_SCALE = 1;
	public static final double PASSIVE_CLIMBER_SPIN_SPEED = 0.07;

	public NathanGain() {
		super("NathanGain");
	}

	protected double scaleGain(double input, double gain, double exp) {
		return Math.pow(Math.abs(input), exp) * gain * Math.signum(input);
	}

	@Override
	public void bindCommands() {
		RobotMap.Component.driverXbox.lb
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.DOWN));
		RobotMap.Component.driverXbox.rb
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.UP));
		Command normalDrive = new ChassisMove(RobotMap.Component.chassis, this);
		RobotMap.Component.driverXbox.dPad.up.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, 180,
			RobotMap.Component.navx, RobotMap.Component.chassisDriveMC));
		RobotMap.Component.driverXbox.dPad.up.whenReleased(normalDrive);
		RobotMap.Component.driverXbox.dPad.down.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, 0,
			RobotMap.Component.navx, RobotMap.Component.chassisDriveMC));
		RobotMap.Component.driverXbox.dPad.down.whenReleased(normalDrive);
		RobotMap.Component.driverXbox.dPad.left.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, 270,
			RobotMap.Component.navx, RobotMap.Component.chassisDriveMC));
		RobotMap.Component.driverXbox.dPad.left.whenReleased(normalDrive);
		RobotMap.Component.driverXbox.dPad.right.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, 90,
			RobotMap.Component.navx, RobotMap.Component.chassisDriveMC));
		RobotMap.Component.driverXbox.dPad.right.whenReleased(normalDrive);
		RobotMap.Component.driverXbox.a.onlyWhileHeld(HumanInterfaceConfig.gearAlign);
		RobotMap.Component.driverXbox.a.whenReleased(normalDrive);
		RobotMap.Component.driverXbox.b.onlyWhileHeld(HumanInterfaceConfig.boilerAlign);
		RobotMap.Component.driverXbox.b.whenReleased(normalDrive);
		RobotMap.Component.driverXbox.y
			.onlyWhileHeld(new ChassisMove(RobotMap.Component.chassis, new ChassisControllerGroup(this, new WiggleApproach())));
		RobotMap.Component.driverXbox.y.whenReleased(normalDrive);
		RobotMap.Component.teensyStick.getButton(0).whenPressed(normalDrive);
		// Inverted (airplane-style) analog gain control
		RobotMap.Component.driverXbox.x
			.onlyWhileReleased(new Climb(() -> Math.max(0, -RobotMap.Component.driverXbox.rightStick.getY())));
		RobotMap.Component.driverXbox.x.onlyWhileHeld(new Climb(() -> NathanGain.PASSIVE_CLIMBER_SPIN_SPEED));
		HumanInterfaceConfig.autoShifter.start();
	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		double rawSpeed = RobotMap.Component.driverXbox.rt.getX() - RobotMap.Component.driverXbox.lt.getX();
		double speed = scaleGain(rawSpeed, NathanGain.SPEED_GAIN, NathanGain.SPEED_EXP)
			* NathanGain.Y_SPEED_SCALE;
		return speed;
	}

	@Override
	public double getTurnSpeed() {
		double rawTurnSpeed = RobotMap.Component.driverXbox.leftStick.getX();
		double turnSpeed = scaleGain(rawTurnSpeed, NathanGain.TURN_GAIN, NathanGain.TURN_EXP)
			* NathanGain.TURN_SPEED_SCALE;
		return turnSpeed;
	}
}
