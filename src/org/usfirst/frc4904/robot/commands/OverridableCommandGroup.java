package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.standard.commands.RunIf;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class OverridableCommandGroup extends CommandGroup {
	public Overridable overridableSubsystem;

	public OverridableCommandGroup(Overridable overridableSubsystem) {
		super();
		this.overridableSubsystem = overridableSubsystem;
	}

	public OverridableCommandGroup(String name, Overridable overridableSubsystem) {
		super(name);
		this.overridableSubsystem = overridableSubsystem;
	}

	public final synchronized void addSequentialUnlessOverridden(Command command) {
		addSequential(new RunIf(command, overridableSubsystem::isNotOverridden));
	}

	public final synchronized void addSequentialUnlessOverridden(Command command, double timeout) {
		addSequential(new RunIf(command, overridableSubsystem::isNotOverridden), timeout);
	}

	public final synchronized void addParallelUnlessOverridden(Command command) {
		addParallel(new RunIf(command, overridableSubsystem::isNotOverridden));
	}

	public final synchronized void addParallelUnlessOverridden(Command command, double timeout) {
		addParallel(new RunIf(command, overridableSubsystem::isNotOverridden), timeout);
	}

	public final synchronized void addSequentialIfOverridden(Command command) {
		addSequential(new RunIf(command, overridableSubsystem::isOverridden));
	}

	public final synchronized void addSequentialIfOverridden(Command command, double timeout) {
		addSequential(new RunIf(command, overridableSubsystem::isOverridden), timeout);
	}

	public final synchronized void addParallelIfOverridden(Command command) {
		addParallel(new RunIf(command, overridableSubsystem::isOverridden));
	}

	public final synchronized void addParallelIfOverridden(Command command, double timeout) {
		addParallel(new RunIf(command, overridableSubsystem::isOverridden), timeout);
	}
}