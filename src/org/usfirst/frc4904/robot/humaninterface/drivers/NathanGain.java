package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.humaninterface.HumanInterfaceConfig;
import org.usfirst.frc4904.sovereignty.TrimCommand;
import org.usfirst.frc4904.sovereignty.TrimCommand.TrimDirection;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.commands.chassis.ChassisShift;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurnAbsolute;
import org.usfirst.frc4904.standard.humaninput.Driver;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import edu.wpi.first.wpilibj.command.Command;

public class NathanGain extends Driver {
	public static final double SPEED_GAIN = 1;
	public static final double TURN_GAIN = 1;
	public static final double SPEED_EXP = 2;
	public static final double TURN_EXP = 2;
	public static final double Y_SPEED_SCALE = 1;
	public static final double TURN_SPEED_SCALE = 1;
	public static final double FINE_SCALE = 2;
	protected final FineModifier modifier = new FineModifier(NathanGain.FINE_SCALE);
	protected final AlignAssist alignAssist = new AlignAssist(HumanInterfaceConfig.gearAlign, modifier);

	public NathanGain() {
		super("NathanGain");
	}

	protected double scaleGain(double input, double gain, double exp) {
		return Math.pow(input, exp) * gain * Math.signum(input);
	}

	@Override
	public void bindCommands() {
		alignAssist.start();
		RobotMap.Component.driverXbox.back.toggleWhenPressed(new Command() {
			@Override
			public void initialize() {
				GearAlign.HELLA = !GearAlign.HELLA;
			}

			@Override
			protected boolean isFinished() {
				return false;
			}
		});
		RobotMap.Component.driverXbox.y.toggleWhenPressed(new EnableFineModifier(modifier));
		RobotMap.Component.driverXbox.a.whenPressed(
			new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.DOWN));
		RobotMap.Component.driverXbox.b
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.UP));
		RobotMap.Component.driverXbox.x.onlyWhileHeld(HumanInterfaceConfig.gearAlign);
		RobotMap.Component.driverXbox.lb.whenPressed(new TrimCommand(HumanInterfaceConfig.gearAlign, TrimDirection.LEFT));
		RobotMap.Component.driverXbox.rb.whenPressed(new TrimCommand(HumanInterfaceConfig.gearAlign, TrimDirection.RIGHT));
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
		LogKitten.wtf("DRIVE SPEED: " + speed);
		return speed;
	}

	@Override
	public double getTurnSpeed() {
		double rawTurnSpeed = RobotMap.Component.driverXbox.leftStick.getX();
		double turnSpeed = scaleGain(modifier.modify(rawTurnSpeed), NathanGain.TURN_GAIN, NathanGain.TURN_EXP)
			* NathanGain.TURN_SPEED_SCALE;
		LogKitten.wtf("TURN SPEED:  " + turnSpeed);
		return turnSpeed;
	}
}
