package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.humaninterface.operators.CheckOperator;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.Noop;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestSubsystems extends Command {
	public static final int totalSubsystems = 12;
	public int currentTestNumber = 0;
	public boolean running = true;
	public Command[] systemTests = new Command[TestSubsystems.totalSubsystems + 1];

	public TestSubsystems() {
		systemTests[0] = new Noop();
		systemTests[1] = new MotorConstant(RobotMap.Component.leftWheel, 0.5);
		systemTests[2] = new MotorConstant(RobotMap.Component.rightWheel, 0.5);
		systemTests[3] = new MotorConstant(RobotMap.Component.ballIO.directionalRoller, 0.5);
		systemTests[4] = new MotorConstant(RobotMap.Component.ballIO.elevatorAndIntakeRoller, 0.5);
		systemTests[5] = new MotorConstant(RobotMap.Component.ballIO.hopperRollers, 0.5);
		systemTests[7] = new GearioOuttake();
		systemTests[8] = new GearioIntake();
		systemTests[9] = new MotorConstant(RobotMap.Component.climber, 0.5);
		systemTests[10] = new BallioDoorSetIntake();
		systemTests[11] = new BallioDoorSetOuttake();
		systemTests[12] = new HopperAgitate();
		for (Command test : systemTests) {
			test = new CommandWithMessage(test, LogKitten.LEVEL_WARN, LogKitten.LEVEL_DEBUG, LogKitten.LEVEL_WARN,
				LogKitten.LEVEL_ERROR);
		}
		LogKitten.w("Initializing TestSubsystem Commands.");
	}

	@Override
	protected void initialize() {
		LogKitten.w("Starting testing action.");
		currentTestNumber = 0;
		CheckOperator.counter = 0;
		running = true;
	}

	@Override
	protected void execute() {
		if (currentTestNumber != CheckOperator.counter) {
			if (CheckOperator.counter >= TestSubsystems.totalSubsystems + 1) {
				systemTests[currentTestNumber].cancel();
				running = false;
			} else {
				LogKitten.w("Changing test.");
				systemTests[currentTestNumber].cancel();
				systemTests[CheckOperator.counter].start();
				currentTestNumber = CheckOperator.counter;
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return !running;
	}

	@Override
	protected void end() {
		LogKitten.w("Testing ended");
	}

	@Override
	protected void interrupted() {
		LogKitten.e("Testing interrupted.");
	}
}
