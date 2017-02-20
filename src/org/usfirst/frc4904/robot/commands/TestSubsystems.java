package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.humaninterface.operators.CheckOperator;
import org.usfirst.frc4904.standard.commands.Noop;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestSubsystems extends Command {
	public static final int totalSubsystems = 2; // WIP
	public static int currentTestNumber = 0;
	public static boolean running = true;
	public static Command systemTests[] = new Command[TestSubsystems.totalSubsystems];

	public TestSubsystems() {
		TestSubsystems.systemTests[0] = new Noop();
		TestSubsystems.systemTests[1] = new CheckMotorConstant(RobotMap.Component.leftWheel,
			RobotMap.Component.leftWheelEncoder, 0.2, 2, 0.1, 2);
		TestSubsystems.systemTests[2] = new CheckMotorConstant(RobotMap.Component.rightWheel,
			RobotMap.Component.rightWheelEncoder, 0.2, 2, 0.1, 2);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		if (TestSubsystems.currentTestNumber != CheckOperator.counter) {
			TestSubsystems.systemTests[TestSubsystems.currentTestNumber].cancel();
			TestSubsystems.systemTests[CheckOperator.counter].start();
			TestSubsystems.currentTestNumber = CheckOperator.counter;
		}
	}

	@Override
	protected boolean isFinished() {
		return !TestSubsystems.running;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
}
