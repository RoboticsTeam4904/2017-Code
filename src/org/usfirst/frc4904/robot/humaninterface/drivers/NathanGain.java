package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.Climb;
import org.usfirst.frc4904.robot.humaninterface.HumanInterfaceConfig;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.commands.chassis.ChassisShift;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurnAbsolute;
import org.usfirst.frc4904.standard.commands.motor.speedmodifiers.SetEnableableModifier;
import org.usfirst.frc4904.standard.humaninput.Driver;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.EnableableModifier;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.LinearModifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class NathanGain extends Driver {
	public static final double SPEED_GAIN = 1;
	public static final double SPEED_EXP = 2;
	public static final double TURN_GAIN = 1;
	public static final double TURN_EXP = 2;
	public static final double CLIMB_GAIN = 1;
	public static final double CLIMB_EXP = 2;
	public static final double Y_SPEED_SCALE = 1;
	public static final double TURN_SPEED_SCALE = 1;
	public static final double FINE_SCALE = 0.5;
	public static final double THIRD_GEAR_ENGAGE_DELAY_SECONDS = 0.2;
	protected final EnableableModifier modifier = new EnableableModifier(new LinearModifier(NathanGain.FINE_SCALE));
	protected final AlignAssist alignAssist = new AlignAssist(HumanInterfaceConfig.gearAlign, modifier);

	public NathanGain() {
		super("NathanGain");
	}

	protected double scaleGain(double input, double gain, double exp) {
		return Math.pow(input, exp) * gain * Math.signum(input);
	}

	@Override
	public void bindCommands() {
		RobotMap.Component.driverXbox.lb
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.DOWN));
		RobotMap.Component.driverXbox.rb
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.UP));
		RobotMap.Component.driverXbox.lb
			.onlyWhileHeld(
				new RunAllSequential(new WaitCommand("Third gear activation delay", NathanGain.THIRD_GEAR_ENGAGE_DELAY_SECONDS),
					new SetEnableableModifier(true, modifier)));
		RobotMap.Component.driverXbox.lb.whenReleased(new SetEnableableModifier(true, modifier));
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
		RobotMap.Component.driverXbox.b.onlyWhileHeld(HumanInterfaceConfig.gearAlign);
		RobotMap.Component.driverXbox.b.whenReleased(normalDrive);
		RobotMap.Component.teensyStick.button1.whenPressed(normalDrive);
		// Inverted (airplane-style) analog gain control
		new Climb(() -> Math.max(0,
			-scaleGain(RobotMap.Component.driverXbox.rightStick.getY(), NathanGain.CLIMB_GAIN, NathanGain.CLIMB_EXP))).start();
		alignAssist.start();
		HumanInterfaceConfig.autoShifter.start();
	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		double rawSpeed = RobotMap.Component.driverXbox.rt.getX() - RobotMap.Component.driverXbox.lt.getX();
		double speed = scaleGain(modifier.modify(rawSpeed), NathanGain.SPEED_GAIN, NathanGain.SPEED_EXP)
			* NathanGain.Y_SPEED_SCALE;
		return speed;
	}

	@Override
	public double getTurnSpeed() {
		double rawTurnSpeed = RobotMap.Component.driverXbox.leftStick.getX();
		double turnSpeed = scaleGain(modifier.modify(rawTurnSpeed), NathanGain.TURN_GAIN, NathanGain.TURN_EXP)
			* NathanGain.TURN_SPEED_SCALE;
		return turnSpeed;
	}
}
