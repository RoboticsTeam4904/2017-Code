package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.Cancel;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonCrossBaseline extends CommandGroup {
	public static final double DISTANCE_BASELINE = 84 * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE;
	
	public AutonCrossBaseline() {
		requires(RobotMap.Component.chassis);
		addParallel(new ChassisMoveDistance(RobotMap.Component.chassis, AutonCrossBaseline.DISTANCE_BASELINE, RobotMap.Component.chassisDrivePID, new Cancel(this), RobotMap.Component.leftWheelEncoder));
	}
}
