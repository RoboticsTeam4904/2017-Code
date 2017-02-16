package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class IndexerLoad extends MotorConstant {
	public IndexerLoad() {
		super(RobotMap.Component.shooter.indexer, Shooter.INDEXER_LOAD_SPEED);
	}
}
