package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Indexer;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class IndexerUnload extends MotorConstant {
	public IndexerUnload() {
		super(RobotMap.Component.indexer, Indexer.UNLOAD_SPEED);
	}
}
