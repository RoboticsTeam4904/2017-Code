package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.standard.LogKitten;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandWithMessage extends Command {
	Object message;
	Command command;
	LogKitten.KittenLevel level;

	public CommandWithMessage(Command command, Object message, LogKitten.KittenLevel level) {
		this.message = message;
		this.level = level;
		this.command = command;
	}

	@Override
	protected void initialize() {
		LogKitten.wtf(message);
		command.start();
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		command.cancel();
	}

	@Override
	protected void interrupted() {
		command.cancel();
	}
}
