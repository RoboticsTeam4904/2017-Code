package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPlaceGearOnPeg1BlueAndPeg3Red extends CommandGroup {
	public static final double MOVE_BASE_IN_INCHES_1 = 2;
	public static final double MOVE_BASE_IN_INCHES_2 = 2;
	public static final int TURN_BASE_IN_DEGREES_1 = 2;

	public AutonPlaceGearOnPeg1BlueAndPeg3Red() {
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonPlaceGearOnPeg1BlueAndPeg3Red.MOVE_BASE_IN_INCHES_1,
			RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis,
				AutonPlaceGearOnPeg1BlueAndPeg3Red.TURN_BASE_IN_DEGREES_1,
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonPlaceGearOnPeg1BlueAndPeg3Red.MOVE_BASE_IN_INCHES_2,
			RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(new GearioOuttake());
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonPlaceGearOnPeg2.DISTANCE_BACK_TO_CLEAR_PEG_IN_INCHES,
			RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}
}
