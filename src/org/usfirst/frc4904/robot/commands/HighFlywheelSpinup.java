package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.HighFlywheel;
import edu.wpi.first.wpilibj.command.Command;

public class HighFlywheelSpinup extends Command {
	public HighFlywheelSpinup() {
		requires(RobotMap.Component.highFlywheel);
	}

	@Override
	public void initialize() {
		RobotMap.Component.highFlywheel.set(HighFlywheel.SHOOTING_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
