package org.usfirst.frc4904.robot.autonomous.strategies;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.BallioOuttake;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPlaceGearAndShootLowGoal extends CommandGroup {
	public AutonPlaceGearAndShootLowGoal() {
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, 38.5, RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(new GearioOuttake());
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, 14.3, RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(new ChassisTurn(RobotMap.Component.chassis, 90, RobotMap.Component.navx,
				RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, 82.44, RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(new ChassisTurn(RobotMap.Component.chassis, 45, RobotMap.Component.navx,
				RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, 14.9, RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(new BallioOuttake());

	}

}
