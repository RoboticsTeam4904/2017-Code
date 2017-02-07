package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.robot.subsystems.GearIO.GearState;
import edu.wpi.first.wpilibj.command.Command;

public class GearSet extends Command {
	GearState state;
	
	public GearSet(GearIO.GearState state) {
		requires(RobotMap.Component.gearIntakeOuttake);
		this.state = state;
	}

	@Override
	protected void execute() {
		RobotMap.Component.gearIntakeOuttake.setGear(state);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
