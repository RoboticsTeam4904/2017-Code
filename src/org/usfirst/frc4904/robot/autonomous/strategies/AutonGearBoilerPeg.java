package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearBoilerPeg extends CommandGroup {
	public AutonGearBoilerPeg() {
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearLoadPeg.MOVE_BASE_IN_INCHES_1, RobotMap.Component.chassisDriveMC));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis, -1 * (AutonGearLoadPeg.TURN_BASE_IN_DEGREES_1),
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearLoadPeg.MOVE_BASE_IN_INCHES_2, RobotMap.Component.chassisDriveMC));
		addSequential(new GearioOuttake());
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPeg.DISTANCE_BACK_TO_CLEAR_PEG_IN_INCHES, RobotMap.Component.chassisDriveMC));
	}
}