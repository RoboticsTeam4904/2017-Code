package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class IndexerUnload extends MotorConstant {
	public IndexerUnload() {
		super("IndexerUnload", RobotMap.Component.shooter.indexer, Shooter.INDEXER_UNLOAD_SPEED);
	}
}
