package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	public final Flywheel flywheel;
	public final Motor indexer;

	public Shooter(Flywheel flywheel, Motor indexer) {
		this.flywheel = flywheel;
		this.indexer = indexer;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
