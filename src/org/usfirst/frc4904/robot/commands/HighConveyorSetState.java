package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.HighConveyor.HighConveyorState;
import edu.wpi.first.wpilibj.command.Command;

public class HighConveyorSetState extends Command {
	private final HighConveyorState state;

	public HighConveyorSetState(HighConveyorState state) {
		this.state = state;
	}

	@Override
	public void initialize() {
		RobotMap.Component.highConveyor.setState(state);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
