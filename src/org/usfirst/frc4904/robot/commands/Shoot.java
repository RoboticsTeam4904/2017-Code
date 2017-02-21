package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.commands.RunAllParallel;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.RunIf;
import org.usfirst.frc4904.standard.commands.WaitUntil;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup implements OverridableCommand {
	public static boolean shootHopperOverride;

	public Shoot() {
		addSequential(new RunIf(new HopperSetShooter(), this::isNotOverridden));
		addSequential(new RunAllParallel(
			new RunFor(new IndexerUnload(), Shooter.INDEXER_UNLOAD_TIME),
			new WaitUntil(RobotMap.Component.shooter.flywheel::isReady)));
		addParallel(new IndexerLoad());
	}

	@Override
	public void setOverride(boolean shootHopperOverride) {
		Shoot.shootHopperOverride = shootHopperOverride;
	}

	@Override
	public boolean isOverridden() {
		return Shoot.shootHopperOverride;
	}
}
