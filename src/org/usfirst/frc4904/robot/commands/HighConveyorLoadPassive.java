package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class HighConveyorLoadPassive extends Command {
	public HighConveyorLoadPassive() {
		requires(RobotMap.Component.highConveyor);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
