package org.usfirst.frc4904.robot.autonomous.strategies;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPlaceGearAndMovePastLine extends CommandGroup {
	public AutonPlaceGearAndMovePastLine() {
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, 38.5, RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(new GearioOuttake());
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, -14.3, RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(new ChassisTurn(RobotMap.Component.chassis, 60, RobotMap.Component.navx,
				RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, 39.1, RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}

}