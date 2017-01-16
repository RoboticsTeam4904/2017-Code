package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.robot.subsystems.GearIO.GearState;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearSet extends Command {
	GearState state;
	
	public GearSet(GearIO.GearState state) {
		requires(RobotMap.Component.gearIntakeOuttake); // Use requires() here to declare subsystem dependencies
		this.state = state;
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		RobotMap.Component.gearIntakeOuttake.setGear(state);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
}
