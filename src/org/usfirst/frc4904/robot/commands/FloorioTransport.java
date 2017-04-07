package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.FloorIO;
import org.usfirst.frc4904.standard.commands.SingleOp;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FloorioTransport extends CommandGroup {
	public FloorioTransport() {
		requires(RobotMap.Component.floorIO);
		addParallel(new MotorConstant(RobotMap.Component.floorIO.roller, 0));
		addParallel(new SingleOp(() -> RobotMap.Component.floorIO.piston.set(FloorIO.RAISED)));
	}
}
