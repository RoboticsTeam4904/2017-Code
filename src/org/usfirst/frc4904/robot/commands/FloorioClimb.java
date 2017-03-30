package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.FloorIO;
import org.usfirst.frc4904.standard.commands.SingleOp;
import org.usfirst.frc4904.standard.commands.WaitWhile;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FloorioClimb extends CommandGroup {
	public FloorioClimb() {
		requires(RobotMap.Component.floorIO);
		addSequential(new SingleOp(() -> RobotMap.Component.floorIO.piston.set(FloorIO.LOWERED)));
		addSequential(new WaitWhile(() -> true));
	}
}
