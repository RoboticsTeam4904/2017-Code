package org.usfirst.frc4904.robot.humaninterface.operators;

import java.util.function.Supplier;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.BallioCycle;
import org.usfirst.frc4904.robot.commands.BallioFloorClear;
import org.usfirst.frc4904.robot.commands.BallioIntake;
import org.usfirst.frc4904.robot.commands.BallioOuttake;
import org.usfirst.frc4904.robot.commands.FlywheelSpinup;
import org.usfirst.frc4904.robot.commands.GearioIntake;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.robot.commands.Shoot;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.standard.humaninput.Operator;

import edu.wpi.first.wpilibj.command.Command;

public class OperatorAriana extends Operator {
	public static final double INTAKE_THRESHOLD = 0.5;

	public OperatorAriana() {
		super("OperatorAriana");
	}

	@Override
	public void bindCommands() {

		RobotMap.Component.operatorStick.button1.onlyWhileHeld(new Shoot());
		// shoots into high goal

		RobotMap.Component.operatorStick.button2.onlyWhileHeld(new FlywheelSpinup());
		// spins up flywheel

		RobotMap.Component.operatorStick.button5.onlyWhileHeld(new BallioIntake());
		// takes from floor and puts in hopper

		RobotMap.Component.operatorStick.button6.onlyWhileHeld(new BallioOuttake());
		// takes balls from hooper and puts in low goal

		RobotMap.Component.operatorStick.button3.onlyWhileHeld(new BallioFloorClear());
		// takes balls from floor and immediately shoots them into low goal

		RobotMap.Component.operatorStick.button4.onlyWhileHeld(new BallioCycle());
		// takes balls from hopper and puts back in hopper (unsticks hopper)
		new ThresholdCommand(new GearioIntake(), RobotMap.Component.operatorStick::getY,
				-OperatorAriana.INTAKE_THRESHOLD).start();
		new ThresholdCommand(new GearioOuttake(), RobotMap.Component.operatorStick::getY,
				OperatorAriana.INTAKE_THRESHOLD, true).start();
		RobotMap.Component.gearIO.setRampState(GearIO.RampState.EXTENDED);
	}

	private class ThresholdCommand extends Command {
		protected final Command command;
		protected final Supplier<Double> axis;
		protected final double threshold;
		protected final boolean invert;

		public ThresholdCommand(Command command, Supplier<Double> axis, double threshold, boolean invert) {
			this.command = command;
			this.axis = axis;
			this.threshold = threshold;
			this.invert = invert;
		}

		public ThresholdCommand(Command command, Supplier<Double> axis, double threshold) {
			this(command, axis, threshold, false);
		}

		protected boolean pastThreshold(double value) {
			return (!invert && value >= threshold) || (invert && value <= threshold);
		}

		@Override
		protected void execute() {
			if (pastThreshold(axis.get()) && !command.isRunning()) {
				command.start();
			} else if (!pastThreshold(axis.get()) && command.isRunning()) {
				command.cancel();
			}
		}

		@Override
		protected boolean isFinished() {
			return false;
		}
	}
}