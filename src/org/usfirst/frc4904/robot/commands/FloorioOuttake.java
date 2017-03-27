package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.FloorIO;
import org.usfirst.frc4904.standard.commands.SingleOp;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FloorioOuttake extends CommandGroup {
	public FloorioOuttake() {
		requires(RobotMap.Component.floorIO);
		addParallel(new MotorConstant(RobotMap.Component.floorIO.roller, FloorIO.OUTTAKE_SPEED));
		addSequential(new WaitCommand(FloorIO.OUTTAKE_ROLLER_DELAY_SECONDS));
		addParallel(new SingleOp(() -> RobotMap.Component.floorIO.piston.set(FloorIO.LOWERED)));
	}
}
