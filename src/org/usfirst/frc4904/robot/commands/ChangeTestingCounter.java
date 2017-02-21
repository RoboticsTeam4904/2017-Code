package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.humaninterface.operators.CheckOperator;
import org.usfirst.frc4904.standard.LogKitten;
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
		LogKitten.wtf("Change " + change);
		CheckOperator.counter += change;
		if (CheckOperator.counter < 0) {
			CheckOperator.counter = 0;
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
