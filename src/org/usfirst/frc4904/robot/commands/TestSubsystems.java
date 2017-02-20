package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.humaninterface.operators.CheckOperator;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestSubsystems extends Command {
	public static final int totalSubsystems = 2; // WIP
	public static int currentTestNumber = 0;
	public static boolean running = true;

	public TestSubsystems() {}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		if (TestSubsystems.currentTestNumber != CheckOperator.counter) {
			if (CheckOperator.counter == 1) {
				new CheckMotorConstant(RobotMap.Component.leftWheel, RobotMap.Component.leftWheelEncoder, 0.2, 2, 0.1, 2)
					.start();
				TestSubsystems.currentTestNumber = 1;
			} else if (CheckOperator.counter == 2) {
				new CheckMotorConstant(RobotMap.Component.rightWheel, RobotMap.Component.rightWheelEncoder, 0.2, 2, 0.1, 2)
					.start();
				TestSubsystems.currentTestNumber = 2;
			} else if (CheckOperator.counter == TestSubsystems.totalSubsystems + 1) {
				TestSubsystems.running = false;
			}
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
