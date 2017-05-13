package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearClear extends CommandGroup {
	public static final double GEAR_CLEAR_FLYWHEEL_SPEED = -0.35;

	public GearClear() {
		addParallel(new RampSet(GearIO.RampState.EXTENDED));
		addParallel(new GearioSet(GearIO.GearState.GEARCLEAR));
		addParallel(new MotorConstant(RobotMap.Component.shooter.flywheel, GearClear.GEAR_CLEAR_FLYWHEEL_SPEED));
	}
}
