package org.usfirst.frc4904.robot.autonomous;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterObjective extends CommandGroup {
	public CenterObjective() {
		requires(RobotMap.Component.ballIO);
		requires(RobotMap.Component.gear)
	}
}
