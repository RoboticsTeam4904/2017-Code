package org.usfirst.frc4904.robot.autonomous;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonCrossBaseline extends CommandGroup {
	public static final double BreakBaselineDist = 84 * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE;
	
	public AutonCrossBaseline() {
		requires(RobotMap.Component.chassis);
		addParallel(new ChassisMoveDistance(RobotMap.Component.chassis, AutonCrossBaseline.BreakBaselineDist, RobotMap.Component.chassisDrivePID, new Kill(this), RobotMap.Component.leftWheelEncoder));
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
