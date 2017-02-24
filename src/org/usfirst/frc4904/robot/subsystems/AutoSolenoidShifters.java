package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class AutoSolenoidShifters extends SolenoidShifters {
	protected long lastManualShift;
	protected long lastAutoShift;

	public AutoSolenoidShifters(DoubleSolenoid solenoid) {
		super(solenoid);
	}

	public AutoSolenoidShifters(DoubleSolenoid solenoid, boolean isInverted) {
		super(solenoid, isInverted);
	}

	public AutoSolenoidShifters(int portUp, int portDown) {
		super(portUp, portDown);
	}

	/**
	 * Trigger a "manual" solenoid shift by toggling.
	 */
	@Override
	public void shift() {
		lastManualShift = System.currentTimeMillis();
		super.shift();
	}

	/**
	 * Trigger a "manual" solenoid shift to the provided state.
	 */
	@Override
	public void shift(ShiftState state) {
		lastManualShift = System.currentTimeMillis();
		super.shift(state);
	}

	/**
	 * Trigger an "auto" solenoid shift by toggling.
	 */
	public void shiftAsAuto() {
		lastAutoShift = System.currentTimeMillis();
		super.shift();
	}

	/**
	 * Trigger an "auto" solenoid shift to the provided state.
	 */
	public void shiftAsAuto(ShiftState state) {
		lastAutoShift = System.currentTimeMillis();
		super.shift(state);
	}

	public long timeSinceLastManualShift() {
		return System.currentTimeMillis() - lastManualShift;
	}

	public long timeSinceLastAutoShift() {
		return System.currentTimeMillis() - lastAutoShift;
	}
}
