package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.command.Command;

public class MatchRecorder extends Command {
	public static final String PREFIX = "MATCH_REC";
	public static final int TICK_THROTTLE = 5;
	private int ticksSinceLast = MatchRecorder.TICK_THROTTLE;
	private int totalTicks = 0;

	public MatchRecorder() {
		setRunWhenDisabled(true);
		setInterruptible(false);
	}

	@Override
	public void execute() {
		if (ticksSinceLast < MatchRecorder.TICK_THROTTLE) {
			ticksSinceLast++;
			return;
		}
		totalTicks++;
		prefixedLog("------MATCH RECORDER//TICK " + totalTicks + "------");
		prefixedLog("PDP voltage = " + RobotMap.Component.pdp.getVoltage());
		prefixedLog("PDP total current = " + RobotMap.Component.pdp.getTotalCurrent());
		prefixedLog("PDP total power = " + RobotMap.Component.pdp.getTotalPower());
		prefixedLog("PDP total energy = " + RobotMap.Component.pdp.getTotalEnergy());
		for (int i = 0; i <= 15; i++) {
			prefixedLog("PDP channel " + i + " current = " + RobotMap.Component.pdp.getCurrent(i));
		}
		prefixedLog("NavX yaw = " + RobotMap.Component.navx.getYaw());
		prefixedLog("NavX y acceleration = " + RobotMap.Component.navx.getWorldLinearAccelY());
		prefixedLog("Right Encoder get = " + RobotMap.Component.rightWheelEncoder.get());
		prefixedLog("Left Encoder get = " + RobotMap.Component.leftWheelEncoder.get());
		Motor[] motorsToRead = {RobotMap.Component.leftWheel, RobotMap.Component.rightWheel, RobotMap.Component.climber,
				RobotMap.Component.gearIO.intakeRoller, RobotMap.Component.lidar};
		for (Motor motor : motorsToRead) {
			prefixedLog("Motor[" + motor.getName() + "] = " + motor.get());
		}
		prefixedLog("Shifters = " + RobotMap.Component.shifter.getShiftState().name());
		prefixedLog("GearIO Gullwings = " + RobotMap.Component.gearIO.getState().name());
		prefixedLog("GearIO Ramp = " + RobotMap.Component.gearIO.getRampState().name());
		ticksSinceLast = 0;
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void prefixedLog(String log) {
		LogKitten.d(MatchRecorder.PREFIX + "(" + totalTicks + "): " + log);
	}
}
