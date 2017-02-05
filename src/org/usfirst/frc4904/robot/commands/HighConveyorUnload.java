package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.HighConveyor;
import edu.wpi.first.wpilibj.command.Command;

public class HighConveyorUnload extends Command {
	public HighConveyorUnload() {
		requires(RobotMap.Component.highConveyor);
	}

	@Override
	public void initialize() {
		RobotMap.Component.highConveyor.set(HighConveyor.UNLOAD_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
