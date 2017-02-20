package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.RunIf;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup implements OverridableCommand {
	public final static double unloadDuration = 0.25; // TODO
	public boolean antiJamOverride;

	public Shoot() {
		addSequential(new HopperSetShooter());
		addParallel(new RunIf(new RunFor(new IndexerUnload(), Shoot.unloadDuration), this::isNotOverridden)); // only anti-jam if not overrided
		addSequential(new Command() {
			@Override
			protected boolean isFinished() {
				return RobotMap.Component.shooter.flywheel.isReady();
			}
		});
		addParallel(new IndexerLoad());
	}

	@Override
	public void setOverride(boolean setValue) {
		antiJamOverride = setValue;
	}

	@Override
	public boolean isOverridden() {
		return antiJamOverride;
	}
}
