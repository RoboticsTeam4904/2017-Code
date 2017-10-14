package org.usfirst.frc4904.robot.commands;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.robot.subsystems.GearIO.RampState;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The RampSet command is run when the robot turns on, lowering the ramp at the
 * back for the sole purpose of allowing a gear to fall into GearIO. The only
 * reason that the ramp isn't always extended down is because of the
 * restrictions of the robot dimensions. However, during the match, a certain
 * amount of extra robot can be extended out of these initial dimensions, which
 * this command utilizes.
 * 
 * @see GearIO
 */

public class RampSet extends Command {
	protected final RampState state;

	public RampSet(RampState state) {
		this.state = state;
		requires(RobotMap.Component.gearIO.ramp);
	}

	@Override
	protected void initialize() {
		RobotMap.Component.gearIO.setRampState(state);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
