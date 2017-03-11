package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.KittenCommand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandWithMessage extends Command {
	Command mainCommand;
	Command startingKittenCommand;
	Command executingKittenCommand;
	Command endingKittenCommand;
	Command interruptedKittenCommand;

	public CommandWithMessage(Command mainCommand, LogKitten.KittenLevel initializeLevel, LogKitten.KittenLevel executeLevel,
		LogKitten.KittenLevel endingLevel, LogKitten.KittenLevel interruptLevel) {
		this.mainCommand = mainCommand;
		startingKittenCommand = new KittenCommand("Starting " + mainCommand.getName(), initializeLevel);
		executingKittenCommand = new KittenCommand("Running " + mainCommand.getName(), executeLevel);
		endingKittenCommand = new KittenCommand("Finishing " + mainCommand.getName(), endingLevel);
		interruptedKittenCommand = new KittenCommand("INTERRUPTED: " + mainCommand.getName(), interruptLevel);
	}

	@Override
	protected void initialize() {
		mainCommand.start();
		startingKittenCommand.start();
	}

	@Override
	protected void execute() {
		executingKittenCommand.start();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		mainCommand.cancel();
		endingKittenCommand.start();
	}

	@Override
	protected void interrupted() {
		mainCommand.cancel();
		interruptedKittenCommand.start();
	}
}
