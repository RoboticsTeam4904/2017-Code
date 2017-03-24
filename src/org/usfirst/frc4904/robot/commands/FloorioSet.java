package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.FloorIO;
import edu.wpi.first.wpilibj.command.Command;

public class FloorioSet extends Command {
	protected final FloorIO.FloorState state;

	public FloorioSet(FloorIO.FloorState state) {
		this.state = state;
		requires(RobotMap.Component.floorIO);
	}

	@Override
	protected void initialize() {
		RobotMap.Component.floorIO.setState(state);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void interrupted() {
		RobotMap.Component.floorIO.setState(FloorIO.FloorState.TRANSPORT);
	}
}
