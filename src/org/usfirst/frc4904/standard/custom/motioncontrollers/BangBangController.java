package org.usfirst.frc4904.standard.custom.motioncontrollers;


import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.Util;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * A bang bang controller.
 * The bang bang controller increases the value of the output
 * if it is below the setpoint or decreases the value of the
 * output if it is above the setpoint.
 *
 */
public class BangBangController extends MotionController {
	protected double error;
	protected double A;
	protected double F;
	protected double threshold;
	
	/**
	 * BangBang controller
	 * A bang bang controller.
	 * The bang bang controller increases the value of the output
	 * if it is below the setpoint or decreases the value of the
	 * output if it is above the setpoint.
	 *
	 * @param source
	 *        Sensor
	 * @param A
	 *        Adjustment term
	 *        The is the amount the setpoint is increase
	 *        or decrease by.
	 * @param F
	 *        Feedforward term
	 *        The scalar on the input.
	 * @param threshold
	 *        The threshold for the bangbang to start doing something.
	 */
	public BangBangController(PIDSource source, double A, double F, double threshold) {
		super(source);
		this.A = A;
		this.F = F;
		this.threshold = threshold;
		reset();
	}
	
	/**
	 * BangBang controller
	 * A bang bang controller.
	 * The bang bang controller increases the value of the output
	 * if it is below the setpoint or decreases the value of the
	 * output if it is above the setpoint.
	 *
	 * @param source
	 *        Sensor
	 * @param A
	 *        Adjustment term
	 *        The is the amount the setpoint is increase
	 *        or decrease by.
	 * @param F
	 *        Feedforward term
	 *        The scalar on the input.
	 */
	public BangBangController(PIDSource source, double A, double F) {
		this(source, A, F, Util.EPSILON);
	}
	
	/**
	 * Sets the setpoint to the current sensor value.
	 */
	@Override
	public void reset() {
		setpoint = source.pidGet();
		error = 0;
	}
	
	/**
	 * Get the current output of the bang bang controller.
	 * This should be used to set the output.
	 *
	 * @return
	 * 		The current output of the bang bang controller.
	 */
	@Override
	public double get() {
		if (!enable) {
			return F * setpoint;
		}
		double input = source.pidGet();
		double error = setpoint - input;
		LogKitten.v(input + " " + setpoint + " " + error);
		if (continuous) {
			double range = inputMax - inputMin;
			// If the error is more than half of the range, it is faster to increase the error and loop around the boundary
			if (Math.abs(error) > range / 2) {
				if (error > 0) {
					error -= range;
				} else {
					error += range;
				}
			}
		}
		if (error < 0.0 && Math.abs(error) > threshold) {
			LogKitten.v("+A " + error);
			return A + F * setpoint;
		} else if (error > 0.0 && Math.abs(error) > threshold) {
			LogKitten.v("-A" + error);
			return -1.0 * A + F * setpoint;
		}
		return F * setpoint;
	}
	
	/**
	 * @return
	 * 		The most recent error.
	 */
	@Override
	public double getError() {
		return error;
	}
}
