package org.usfirst.frc4904.robot.autonomous;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.BallOuttake;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonVomitBlue extends CommandGroup {
	public AutonVomitBlue() {
		requires(RobotMap.Component.chassis);
		requires(RobotMap.Component.ballIO);
		addParallel(new ChassisMoveDistance(RobotMap.Component.chassis, AutonVomitRed.distance1, RobotMap.Component.chassisDrivePID, new Kill(this), RobotMap.Component.leftWheelEncoder));// in inches
		addSequential(new ChassisTurn(RobotMap.Component.chassis, AutonVomitRed.turnAngle * AutonVomitSubsystem.ChassisAlliance.BLUE.value, RobotMap.Component.navX, new Kill(this), RobotMap.Component.chassisDrivePID));// in degrees
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, AutonVomitRed.distance2, RobotMap.Component.chassisDrivePID, new Kill(this), RobotMap.Component.leftWheelEncoder));// ChassisDrivePID is not finished
		addSequential(new BallOuttake());// time to vomit
		addSequential(new WaitCommand(RobotMap.Constant.AutonomousMetric.WAIT_TIME));
		addParallel(new ChassisMoveDistance(RobotMap.Component.chassis, -1 * AutonVomitRed.distance2, RobotMap.Component.chassisDrivePID, new Kill(this), RobotMap.Component.leftWheelEncoder));
		addSequential(new ChassisTurn(RobotMap.Component.chassis, (180 - AutonVomitRed.turnAngle) * AutonVomitSubsystem.ChassisAlliance.BLUE.value, RobotMap.Component.navX, new Kill(this), RobotMap.Component.chassisDrivePID));// in degrees
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, AutonVomitRed.distance3, RobotMap.Component.chassisDrivePID, new Kill(this), RobotMap.Component.leftWheelEncoder));
	}
}
