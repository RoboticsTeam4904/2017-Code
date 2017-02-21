package org.usfirst.frc4904.robot.humaninterface.operators;


import java.util.function.Supplier;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.BallioCycle;
import org.usfirst.frc4904.robot.commands.BallioFloorClear;
import org.usfirst.frc4904.robot.commands.BallioIntake;
import org.usfirst.frc4904.robot.commands.BallioOuttake;
import org.usfirst.frc4904.robot.commands.GearioIntake;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.robot.commands.HopperSetBallio;
import org.usfirst.frc4904.robot.commands.HopperSetShooter;
import org.usfirst.frc4904.robot.commands.SetOverride;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.standard.humaninput.Operator;
import edu.wpi.first.wpilibj.command.Command;

public class DefaultOperator extends Operator {
	public static final double INTAKE_THRESHOLD = 0.5;

	public DefaultOperator() {
		super("DefaultOperator");
	}

	@Override
	public void bindCommands() {
		RobotMap.Component.operatorStick.button3.onlyWhileHeld(new BallioIntake());
		RobotMap.Component.operatorStick.button4.onlyWhileHeld(new BallioOuttake());
		RobotMap.Component.operatorStick.button5.onlyWhileHeld(new BallioFloorClear());
		RobotMap.Component.operatorStick.button6.onlyWhileHeld(new BallioCycle());
		new ThresholdCommand(new GearioIntake(), RobotMap.Component.operatorStick::getY, DefaultOperator.INTAKE_THRESHOLD)
			.start();
		new ThresholdCommand(new GearioOuttake(), RobotMap.Component.operatorStick::getY, -DefaultOperator.INTAKE_THRESHOLD,
			true)
				.start();
		RobotMap.Component.gearIO.setRampState(GearIO.RampState.EXTENDED);
		RobotMap.Component.teensyStick.getButton(6).whenPressed(new SetOverride(true, new BallioOuttake(), new BallioCycle()));
		RobotMap.Component.teensyStick.getButton(6)
			.whenReleased(new SetOverride(false, new BallioOuttake(), new BallioCycle()));
		RobotMap.Component.teensyStick.getButton(7).whenPressed(new HopperSetBallio());
		RobotMap.Component.teensyStick.getButton(8).whenPressed(new HopperSetShooter());
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