package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.command.Command;

/**
 * TEMPORARY - DO NOT MERGE
 */
public class ErikLogger extends Command {
	public ErikLogger() {
		setInterruptible(false);
	}

	@Override
	public void execute() {
		LogKitten.d("PDP voltage: " + RobotMap.Component.pdp.getVoltage());
		LogKitten.d("PDP temperature: " + RobotMap.Component.pdp.getTemperature());
		LogKitten.d("PDP total current: " + RobotMap.Component.pdp.getTotalCurrent());
		LogKitten.d("PDP total power: " + RobotMap.Component.pdp.getTotalPower());
		LogKitten.d("PDP total energy: " + RobotMap.Component.pdp.getTotalEnergy());
		for (int i = 0; i <= 15; i++) {
			LogKitten.d("PDP channel " + i + " current: " + RobotMap.Component.pdp.getCurrent(i));
		}
		LogKitten.d("NavX yaw: " + RobotMap.Component.navx.getYaw());
		LogKitten.d("NavX y acceleration: " + RobotMap.Component.navx.getWorldLinearAccelY());
		LogKitten.d("Right Encoder get: " + RobotMap.Component.rightWheelEncoder.get());
		LogKitten.d("Left Encoder get: " + RobotMap.Component.leftWheelEncoder.get());
		Motor[] motors = {RobotMap.Component.leftWheel, RobotMap.Component.rightWheel,
				RobotMap.Component.ballIO.directionalRoller, RobotMap.Component.ballIO.elevatorAndIntakeRoller,
				RobotMap.Component.ballIO.hopperRollers};
		for (Motor motor : motors) {
			LogKitten.d("Motor get (" + motor.getName() + "): " + motor.get());
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
