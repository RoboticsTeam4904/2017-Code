package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.command.Command;

/**
 * TEMPORARY - DO NOT MERGE
 */
public class ErikLogger extends Command {
	public static final String PREFIX = "Erik";
	public static final int TICK_THROTTLE = 5;
	private int ticksSinceLast = ErikLogger.TICK_THROTTLE;
	private int totalTicks = 0;

	public ErikLogger() {
		setRunWhenDisabled(true);
		setInterruptible(false);
	}

	@Override
	public void initialize() {}

	@Override
	public void execute() {
		if (ticksSinceLast < ErikLogger.TICK_THROTTLE) {
			ticksSinceLast++;
			return;
		}
		totalTicks++;
		prefixedLog("-------------------");
		prefixedLog("PDP voltage: " + RobotMap.Component.pdpErikLogger.getVoltage());
		prefixedLog("PDP temperature: " + RobotMap.Component.pdpErikLogger.getTemperature());
		prefixedLog("PDP total current: " + RobotMap.Component.pdpErikLogger.getTotalCurrent());
		prefixedLog("PDP total power: " + RobotMap.Component.pdpErikLogger.getTotalPower());
		prefixedLog("PDP total energy: " + RobotMap.Component.pdpErikLogger.getTotalEnergy());
		for (int i = 0; i <= 15; i++) {
			prefixedLog("PDP channel " + i + " current: " + RobotMap.Component.pdpErikLogger.getCurrent(i));
		}
		prefixedLog("NavX yaw: " + RobotMap.Component.navx.getYaw());
		prefixedLog("NavX y acceleration: " + RobotMap.Component.navx.getWorldLinearAccelY());
		prefixedLog("Right Encoder get: " + RobotMap.Component.rightWheelEncoder.get());
		prefixedLog("Left Encoder get: " + RobotMap.Component.leftWheelEncoder.get());
		Motor[] motors = {RobotMap.Component.leftWheel, RobotMap.Component.rightWheel,
				RobotMap.Component.ballIO.directionalRoller, RobotMap.Component.ballIO.elevatorAndIntakeRoller,
				RobotMap.Component.ballIO.hopperRollers};
		for (Motor motor : motors) {
			prefixedLog("Motor get (" + motor.getName() + "): " + motor.get());
		}
		ticksSinceLast = 0;
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void prefixedLog(String log) {
		LogKitten.d(ErikLogger.PREFIX + "(" + totalTicks + "): " + log);
	}
}
