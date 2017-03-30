package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.FloorIO;
import org.usfirst.frc4904.standard.commands.SingleOp;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FloorioSecureGear extends CommandGroup {
	public FloorioSecureGear() {
		setTimeout(FloorIO.SECURE_GEAR_TIME);
		requires(RobotMap.Component.floorIO);
		addParallel(new SingleOp(() -> RobotMap.Component.floorIO.piston.set(FloorIO.RAISED)));
		addParallel(new MotorConstant(RobotMap.Component.floorIO.roller, FloorIO.SECURE_GEAR_SPEED));
	}

	@Override
	protected boolean isFinished() {
		return super.isFinished() || isTimedOut();
	}
}
