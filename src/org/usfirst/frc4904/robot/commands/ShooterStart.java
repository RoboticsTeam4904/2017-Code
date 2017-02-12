package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.RunIf;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShooterStart extends CommandGroup implements OverridableCommand {
	public final static double unloadDuration = 0.25; // TODO
	public boolean antiJamOverride;

	public ShooterStart() {
		addParallel(new FlywheelSpinup());
		addParallel(new RunIf(new RunFor(new IndexerUnload(), ShooterStart.unloadDuration), this::getNotOverride));// only anti-jam if not overrided
		addSequential(new Command() {
			@Override
			protected boolean isFinished() {
				return RobotMap.Component.flywheel.isReady();
			}
		});
		addParallel(new IndexerLoad());
	}

	@Override
	public void setOverride(boolean setValue) {
		antiJamOverride = setValue;
	}

	@Override
	public boolean getOverride() {
		return antiJamOverride;
	}

	public boolean getNotOverride() {
		return !antiJamOverride;
	}
}
