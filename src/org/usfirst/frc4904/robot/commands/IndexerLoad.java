package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Indexer;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class IndexerLoad extends MotorConstant {
	public IndexerLoad() {
		super(RobotMap.Component.indexer, Indexer.LOAD_SPEED);
	}
}
