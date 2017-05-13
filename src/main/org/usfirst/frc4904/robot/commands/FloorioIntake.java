package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.FloorIO;
import org.usfirst.frc4904.standard.commands.SingleOp;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FloorioIntake extends CommandGroup {
	public FloorioIntake() {
		requires(RobotMap.Component.floorIO);
		addParallel(new MotorConstant(RobotMap.Component.floorIO.roller, FloorIO.INTAKE_SPEED));
		addParallel(new SingleOp(() -> RobotMap.Component.floorIO.piston.set(FloorIO.LOWERED)));
	}

	@Override
	protected void interrupted() {
		new FloorioSecureGear().start();
	}
}
