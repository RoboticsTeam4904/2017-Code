package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.standard.commands.RunIf;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShooterStart extends CommandGroup implements OverridableCommand {
	public boolean flywheelOverride = false;

	public ShooterStart() {
		addParallel(new RunIf(new FlywheelSpinup(NathanGain.shooterSetValue), this::getOverride));
		addParallel(new RunIf(new FlywheelSpinup(), this::getNotOverride));
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
		flywheelOverride = setValue;
	}

	@Override
	public boolean getOverride() {
		return flywheelOverride;
	}

	public boolean getNotOverride() {
		return !flywheelOverride;
	}
}
