package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.Util;
import org.usfirst.frc4904.standard.custom.ChassisController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifier;

public class DriveStraightSpeedModifier implements SpeedModifier {
	protected final ChassisController controller;
	protected final MotionController motionController;

	public DriveStraightSpeedModifier(ChassisController controller, MotionController motionController) {
		this.controller = controller;
		this.motionController = motionController;
	}

	@Override
	public double modify(double speed) {
		// If we're driving straight, use drive straight PID
		if (Util.isZero(controller.getY()) && Util.isZero(controller.getX())) {
			// Tell the motion controller to hold the current heading
			motionController.setSetpoint(motionController.getSensorValue());
			// Enable the motion controller if it's not already
			if (!motionController.isEnabled()) {
				motionController.enable();
			}
			// Return the motion controller's desired output
			try {
				return motionController.getSafely();
			}
			// If we can't, just log an exception (we'll return the controller's turn speed down below)
			catch (InvalidSensorException e) {
				LogKitten.ex(e);
			}
			// If we're not going straight, make sure the motion controller is off
		} else if (motionController.isEnabled()) {
			motionController.disable();
		}
		// By default, return the controller's turn speed
		return controller.getTurnSpeed();
	}
}
