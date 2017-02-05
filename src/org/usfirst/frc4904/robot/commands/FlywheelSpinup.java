package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;

public class FlywheelSpinup extends Command {
	public FlywheelSpinup() {
		requires(RobotMap.Component.flywheel);
	}

	@Override
	public void initialize() {
		RobotMap.Component.flywheel.set(Flywheel.SHOOTING_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
