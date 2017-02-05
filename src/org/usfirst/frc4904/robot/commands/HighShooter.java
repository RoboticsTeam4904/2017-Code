package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.HighConveyor;
import org.usfirst.frc4904.robot.subsystems.HighConveyor.HighConveyorState;
import org.usfirst.frc4904.robot.subsystems.HighFlywheel.HighFlywheelState;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class HighShooter extends CommandGroup {
	public HighShooter() {
		RunAllSequential sequential = new RunAllSequential(
			new RunFor(new HighConveyorSetState(HighConveyorState.UNLOAD), HighConveyor.UNLOAD_TIME),
			new HighConveyorSetState(HighConveyorState.ACTIVE));
		addSequential(sequential);
	}

	@Override
	public void initialize() {
		RobotMap.Component.highFlywheel.setState(HighFlywheelState.ACTIVE);
	}

	@Override
	public void execute() {
		if (RobotMap.Component.highConveyor.getState() == HighConveyorState.IDLE ||
			RobotMap.Component.highConveyor.getState() == HighConveyorState.PASSIVE) {
			RobotMap.Component.highConveyor.setState(HighConveyorState.UNLOAD);
		}
	}
}
