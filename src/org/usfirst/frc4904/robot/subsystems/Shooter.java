package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.OverridableSubsystem;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;

public class Shooter extends OverridableSubsystem {
	public final Flywheel flywheel;
	public final Motor indexer;
	public static final double INDEXER_LOAD_SPEED = 1;
	public static final double INDEXER_UNLOAD_SPEED = -0.5;
	public static final double INDEXER_UNLOAD_TIME = 0.2;

	public Shooter(Flywheel flywheel, Motor indexer) {
		this.flywheel = flywheel;
		this.indexer = indexer;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
