package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonDrivePastBaseline extends CommandGroup {
	public static final double DISTANCE_TO_CLEAR_BASELINE_IN_INCHES = 90;

	public AutonDrivePastBaseline() {
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, AutonDrivePastBaseline.DISTANCE_TO_CLEAR_BASELINE_IN_INCHES,
				RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}
}
