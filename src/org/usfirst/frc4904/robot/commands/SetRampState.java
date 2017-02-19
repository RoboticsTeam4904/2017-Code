package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO.RampState;
import edu.wpi.first.wpilibj.command.Command;

public class SetRampState extends Command {
	public SetRampState(RampState state) {
		RobotMap.Component.gearIO.setRampState(state);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
