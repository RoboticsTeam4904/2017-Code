package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class IndexerLoad extends CommandGroup {
	public IndexerLoad() {
		super("IndexerLoad");
		addParallel(new MotorConstant(RobotMap.Component.shooter.indexer, Shooter.INDEXER_LOAD_SPEED));
		addParallel(new MotorConstant(RobotMap.Component.shooter.windexer, Shooter.WINDEXER_LOAD_SPEED));
	}
}
