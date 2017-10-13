package org.usfirst.frc4904.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;

import edu.wpi.first.wpilibj.command.Command;

/**
 * sets the Climb motor speed to the joystick input value
 *
 */
// TODO: @mickey clean this up
public class Climb extends MotorSet {
	protected final Supplier<Double> axis;
	protected final Command rampCommand = new RampSet(GearIO.RampState.RETRACTED);
	protected final Command floorioCommand = new FloorioClimb();
	protected static final double MINIMUM_AXIS_VALUE_TO_FLIP_GEARIO_RAMP = 0.25;

	/**
	 * 
	 * @param axis
	 *            is a joystick control axis for climbing
	 */
	public Climb(Supplier<Double> axis) {
		super(RobotMap.Component.climber);// gives MotorSet (Parent class) the
											// climber motor
		this.axis = axis;
	}

	@Override
	protected void initialize() {
	}

	/**
	 * Start rampCommand if not started, start floorioCommand if not started.
	 * Run the climb motor at the speed from the joystick.
	 */
	@Override
	protected void execute() {
		set(Math.max(0, axis.get())); // No, really, always feed positive values
										// to the climber motor
		if (axis.get() >= Climb.MINIMUM_AXIS_VALUE_TO_FLIP_GEARIO_RAMP && RobotMap.Component.gearIO.isNotOverridden()
				&& !rampCommand.isRunning()) {
			rampCommand.start();
		}
		if (axis.get() > 0 && !floorioCommand.isRunning()) {
			floorioCommand.start();
		}
		super.execute();
	}
}
