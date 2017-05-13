package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.FloorIO;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Prepare FloorIO for climb and hold it in the LOWERED state.
 *
 */
public class FloorioClimb extends Command {
	public FloorioClimb() {
		requires(RobotMap.Component.floorIO);
	}

	@Override
	protected void initialize() {
		RobotMap.Component.floorIO.piston.set(FloorIO.LOWERED);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
