package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.RunIfElse;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup implements OverridableCommand {
	public Shoot() {
		addSequential(
			new RunIfElse(new FlywheelSpinup(), new FlywheelSpinup(NathanGain.shooterSetValue), this::isNotOverridden));
		addSequential(new HopperSetShooter());
		addParallel(new RunFor(new IndexerUnload(), Shooter.INDEXER_UNLOAD_TIME));
		addSequential(new Command() {
			@Override
			protected boolean isFinished() {
				return RobotMap.Component.shooter.flywheel.isReady();
			}
		});
		addParallel(new IndexerLoad());
	}
	public boolean flywheelOverride = false;

	@Override
	public void setOverride(boolean setValue) {
		flywheelOverride = setValue;
	}

	@Override
	public boolean isOverridden() {
		return flywheelOverride;
	}
}
