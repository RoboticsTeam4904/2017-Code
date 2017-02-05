package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj.command.Command;

public class IndexerUnload extends Command {
	public IndexerUnload() {
		requires(RobotMap.Component.indexer);
	}

	@Override
	public void initialize() {
		RobotMap.Component.indexer.set(Indexer.UNLOAD_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
