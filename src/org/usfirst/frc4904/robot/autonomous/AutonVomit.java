package org.usfirst.frc4904.robot.autonomous;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.DumpDown;
import org.usfirst.frc4904.robot.commands.DumpUp;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonVomit extends CommandGroup {// Vomit autonomously
	public static final double distance1 = 67.5 * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE;
	public static final double distance2 = 73.18 * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE;
	public static final double distance3 = -15 * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE;
	
	public AutonVomit() {
		requires(RobotMap.Component.chassis);
		requires(RobotMap.Component.ballDumper);
		addParallel(new ChassisMoveDistance(RobotMap.Component.chassis, AutonVomit.distance1, RobotMap.Component.chassisDrivePID, new Kill(this), RobotMap.Component.leftWheelEncoder));// in inches
		addSequential(new ChassisTurn(RobotMap.Component.chassis, 135, RobotMap.Component.navX, new Kill(this), RobotMap.Component.chassisDrivePID));// in degrees
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, AutonVomit.distance2, RobotMap.Component.chassisDrivePID, new Kill(this), RobotMap.Component.leftWheelEncoder));// CHANGE TIMPID LATER
		addSequential(new DumpUp());
		addSequential(new WaitCommand(RobotMap.Constant.AutonomousMetric.WAIT_TIME));
		addSequential(new DumpDown());
		addParallel(new ChassisMoveDistance(RobotMap.Component.chassis, -1 * AutonVomit.distance2, RobotMap.Component.chassisDrivePID, new Kill(this), RobotMap.Component.leftWheelEncoder));
		addSequential(new ChassisTurn(RobotMap.Component.chassis, 45, RobotMap.Component.navX, new Kill(this), RobotMap.Component.chassisDrivePID));// in degrees
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, AutonVomit.distance3, RobotMap.Component.chassisDrivePID, new Kill(this), RobotMap.Component.leftWheelEncoder));
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {}
}
