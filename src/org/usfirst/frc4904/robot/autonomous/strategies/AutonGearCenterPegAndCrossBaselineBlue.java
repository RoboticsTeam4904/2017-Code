package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearCenterPegAndCrossBaselineBlue extends CommandGroup {
	public AutonGearCenterPegAndCrossBaselineBlue() {
		addSequential(new AutonGearCenterPeg());
		addSequential(new ChassisTurn(RobotMap.Component.chassis,
			-1 * (AutonGearCenterPegAndCrossBaselineRed.DEGREES_OF_ANGLE_TO_PASS_BASELINE), RobotMap.Component.navx,
			RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPegAndCrossBaselineRed.DISTANCE_FORWARD_FROM_TURN_IN_INCHES, RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}
}
