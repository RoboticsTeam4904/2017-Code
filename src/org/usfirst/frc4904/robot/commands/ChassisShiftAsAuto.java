package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.AutoSolenoidShifters;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command should be used by the AutoShifter to shift a set of solenoids.
 * It is the same as ChassisShift, except that it interacts differently with the 'last shift' time saved in the AutoSolenoidShifters.
 */
public class ChassisShiftAsAuto extends Command {
	protected final AutoSolenoidShifters solenoids;
	protected final SolenoidShifters.ShiftState state;

	/**
	 * Toggles the solenoids and marks the shift as auto.
	 * 
	 * @param solenoids
	 */
	public ChassisShiftAsAuto(AutoSolenoidShifters solenoids) {
		this(solenoids, null);
	}

	/**
	 * Shifts the solenoids to the provided state and marks the shift as auto.
	 *
	 * @param solenoids
	 * @param state
	 */
	public ChassisShiftAsAuto(AutoSolenoidShifters solenoids, SolenoidShifters.ShiftState state) {
		super("ChassisShiftAsAuto");
		this.solenoids = solenoids;
		requires(solenoids);
		setInterruptible(true);
		this.state = state;
	}

	@Override
	protected void initialize() {
		if (state == null) {
			// null state means toggle
			solenoids.shiftAsAuto();
		} else {
			// not null state means shift to it directly
			solenoids.shiftAsAuto(state);
		}
	}

	@Override
	protected boolean isFinished() {
		return false; // Encoders stay in whatever state until shifted elsewhere.
	}
}
