package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import edu.wpi.first.wpilibj.command.Command;

public class GearioSet extends Command {
	protected final GearIO.GearState state;

	public GearioSet(GearIO.GearState state) {
		this.state = state;
		requires(RobotMap.Component.gearIO);
	}

	@Override
	protected void execute() {
		RobotMap.Component.gearIO.setState(state);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void interrupted() {
		RobotMap.Component.gearIO.setState(GearIO.GearState.TRANSPORT);
	}
}
