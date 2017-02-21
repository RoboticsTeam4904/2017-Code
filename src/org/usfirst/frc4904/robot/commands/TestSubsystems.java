package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.humaninterface.operators.CheckOperator;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.Noop;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestSubsystems extends Command {
	public static final int totalSubsystems = 2; // WIP
	public int currentTestNumber = 0;
	public boolean running = true;
	public Command[] systemTests = new Command[TestSubsystems.totalSubsystems + 1];

	public TestSubsystems() {
		systemTests[0] = new Noop();
		// systemTests[1] = new CheckMotorConstant(RobotMap.Component.leftWheel,
		// RobotMap.Component.leftWheelEncoder, 0.2, 2, 0.1, 2);
		// systemTests[2] = new CheckMotorConstant(RobotMap.Component.rightWheel,
		// RobotMap.Component.rightWheelEncoder, 0.2, 2, 0.1, 2);
		LogKitten.wtf("Initializing TestSubsystem Commands.");
	}

	@Override
	protected void initialize() {
		LogKitten.wtf("Starting testing action.");
		currentTestNumber = 0;
		CheckOperator.counter = 0;
		running = true;
	}

	@Override
	protected void execute() {
		if (currentTestNumber != CheckOperator.counter) {
			LogKitten.wtf("current " + currentTestNumber);
			LogKitten.wtf("counter " + CheckOperator.counter);
			LogKitten.wtf("Difference");
			if (CheckOperator.counter >= TestSubsystems.totalSubsystems + 1) {
				systemTests[currentTestNumber].cancel();
				running = false;
			} else {
				LogKitten.wtf("Real difference");
				systemTests[currentTestNumber].cancel();
				LogKitten.wtf("Thing being cancelled: " + systemTests[currentTestNumber].getName());
				LogKitten.wtf(systemTests[CheckOperator.counter].getName());
				LogKitten.wtf("Thing being started: " + systemTests[CheckOperator.counter].getName());
				systemTests[CheckOperator.counter].start();
				currentTestNumber = CheckOperator.counter;
			}
		}
		// LogKitten.wtf(CheckOperator.counter);
	}

	@Override
	protected boolean isFinished() {
		return !running;
	}

	@Override
	protected void end() {
		LogKitten.wtf("Testing ended");
	}

	@Override
	protected void interrupted() {
		LogKitten.wtf("Testing interrupted.");
	}
}
