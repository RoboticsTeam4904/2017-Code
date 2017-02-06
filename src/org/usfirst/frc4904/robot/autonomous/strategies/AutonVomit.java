package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.BallOuttake;
import org.usfirst.frc4904.standard.commands.Cancel;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonVomit extends CommandGroup {// Vomit autonomously
	public static final double DISTANCE_START_TO_POINT = 67.5 * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE;
	public static final double DISTANE_POINT_TO_BOILER = 73.18 * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE;
	public static final double DISTANCE_POINT_TO_BASELINE = -15 * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE;
	public static final double BOILER_TURN_ANGLE = 135;
	
	public static enum Alliance {
		RED(1), BLUE(-1);
		public final int value;
		
		private Alliance(int value) {
			this.value = value;
		}
	}
	
	public AutonVomit(Alliance alliance) {
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, AutonVomit.DISTANCE_START_TO_POINT, RobotMap.Component.chassisDrivePID, new Cancel(this), RobotMap.Component.leftWheelEncoder));// in inches
		addSequential(new ChassisTurn(RobotMap.Component.chassis, AutonVomit.BOILER_TURN_ANGLE * alliance.value, RobotMap.Component.navX, new Cancel(this), RobotMap.Component.chassisDrivePID));// in degrees
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, AutonVomit.DISTANE_POINT_TO_BOILER, RobotMap.Component.chassisDrivePID, new Cancel(this), RobotMap.Component.leftWheelEncoder));// ChassisDrivePID is not finished
		addSequential(new BallOuttake());// time to vomit
		addSequential(new WaitCommand(RobotMap.Constant.AutonomousMetric.WAIT_TIME));
		addParallel(new ChassisMoveDistance(RobotMap.Component.chassis, -1 * AutonVomit.DISTANE_POINT_TO_BOILER, RobotMap.Component.chassisDrivePID, new Cancel(this), RobotMap.Component.leftWheelEncoder));
		addSequential(new ChassisTurn(RobotMap.Component.chassis, (180 - AutonVomit.BOILER_TURN_ANGLE) * alliance.value, RobotMap.Component.navX, new Cancel(this), RobotMap.Component.chassisDrivePID));// in degrees
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, AutonVomit.DISTANCE_POINT_TO_BASELINE, RobotMap.Component.chassisDrivePID, new Cancel(this), RobotMap.Component.leftWheelEncoder));
	}
}
