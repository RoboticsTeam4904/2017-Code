package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	public final Flywheel flywheel;
	public final Motor indexer;
	public final Motor windexer;
	public static final double INDEXER_LOAD_SPEED = 1;
	public static final double INDEXER_UNLOAD_SPEED = -0.5;
	public static final double INDEXER_UNLOAD_TIME = 0.2;
	public static final double WINDEXER_LOAD_SPEED = 0.45;

	public Shooter(Flywheel flywheel, Motor indexer, Motor windexer) {
		this.flywheel = flywheel;
		this.indexer = indexer;
		this.windexer = windexer;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
