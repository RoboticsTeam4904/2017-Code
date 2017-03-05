package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.standard.commands.RunIf;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class OverrideableCommandGroup extends CommandGroup implements Overridable {
	protected volatile boolean isOverriden;

	public OverrideableCommandGroup() {
		super();
	}

	public OverrideableCommandGroup(String name) {
		super(name);
	}

	@Override
	final public synchronized boolean isOverridden() {
		return isOverriden;
	}

	@Override
	final public synchronized void setOverridden(boolean isOverriden) {
		this.isOverriden = isOverriden;
	}

	public final synchronized void addSequentialUnlessOverridden(Command command) {
		addSequential(new RunIf(command, this::isNotOverridden));
	}

	public final synchronized void addSequentialUnlessOverridden(Command command, double timeout) {
		addSequential(new RunIf(command, this::isNotOverridden), timeout);
	}

	public final synchronized void addParallelUnlessOverridden(Command command) {
		addParallel(new RunIf(command, this::isNotOverridden));
	}

	public final synchronized void addParallelUnlessOverridden(Command command, double timeout) {
		addParallel(new RunIf(command, this::isNotOverridden), timeout);
	}

	public final synchronized void addSequentialIfOverridden(Command command) {
		addSequential(new RunIf(command, this::isOverridden));
	}

	public final synchronized void addSequentialIfOverridden(Command command, double timeout) {
		addSequential(new RunIf(command, this::isOverridden), timeout);
	}

	public final synchronized void addParallelIfOverridden(Command command) {
		addParallel(new RunIf(command, this::isOverridden));
	}

	public final synchronized void addParallelIfOverridden(Command command, double timeout) {
		addParallel(new RunIf(command, this::isOverridden), timeout);
	}
}
