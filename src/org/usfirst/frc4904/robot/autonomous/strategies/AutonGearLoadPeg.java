package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearLoadPeg extends CommandGroup {
	public static final double MOVE_BASE_IN_INCHES_1 = 93.3;
	public static final double MOVE_BASE_IN_INCHES_2 = 120;
	public static final int TURN_BASE_IN_DEGREES_1 = 46;

	public AutonGearLoadPeg() {
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearLoadPeg.MOVE_BASE_IN_INCHES_1, RobotMap.Component.chassisDriveMC));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis,
				AutonGearLoadPeg.TURN_BASE_IN_DEGREES_1,
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearLoadPeg.MOVE_BASE_IN_INCHES_2, RobotMap.Component.chassisDriveMC));
		addSequential(new GearioOuttake());
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPeg.DISTANCE_BACK_TO_CLEAR_PEG_IN_INCHES, RobotMap.Component.chassisDriveMC));
	}
}
