package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.standard.commands.RunIf;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class OverrideableCommandGroup extends CommandGroup {
	protected volatile boolean isOverriden;

	final public synchronized boolean isOverriden() {
		return isOverriden;
	}

	final public synchronized void setOverriden(boolean isOverriden) {
		this.isOverriden = isOverriden;
	}

	private boolean isNotOverriden() {
		return !isOverriden();
	}

	public final synchronized void addSequentialUnlessOverridden(Command command) {
		addSequential(new RunIf(command, this::isNotOverriden));
	}

	public final synchronized void addSequentialUnlessOverridden(Command command, double timeout) {
		addSequential(new RunIf(command, this::isNotOverriden), timeout);
	}

	public final synchronized void addParallelUnlessOverridden(Command command) {
		addParallel(new RunIf(command, this::isNotOverriden));
	}

	public final synchronized void addParallelUnlessOverridden(Command command, double timeout) {
		addParallel(new RunIf(command, this::isNotOverriden), timeout);
	}

	public final synchronized void addSequentialIfOverridden(Command command) {
		addSequential(new RunIf(command, this::isOverriden));
	}

	public final synchronized void addSequentialIfOverridden(Command command, double timeout) {
		addSequential(new RunIf(command, this::isOverriden), timeout);
	}

	public final synchronized void addParallelIfOverridden(Command command) {
		addParallel(new RunIf(command, this::isOverriden));
	}

	public final synchronized void addParallelIfOverridden(Command command, double timeout) {
		addParallel(new RunIf(command, this::isOverriden), timeout);
	}
}
