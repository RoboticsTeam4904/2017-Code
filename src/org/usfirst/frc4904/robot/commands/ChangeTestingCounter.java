package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.humaninterface.operators.CheckOperator;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeTestingCounter extends Command {
	int change;

	public ChangeTestingCounter(int change) {
		this.change = change;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		CheckOperator.counter += change;
		if (CheckOperator.counter < 1) {
			CheckOperator.counter = 1;
		} else if (CheckOperator.counter > TestSubsystems.totalSubsystems + 1) {
			CheckOperator.counter = TestSubsystems.totalSubsystems + 1;
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
}
