package org.usfirst.frc4904.robot;


import java.util.Arrays;
import org.usfirst.frc4904.standard.custom.ChassisController;

/**
 * A composite ChassisController class.
 * Each ChassisController method returns the sum of the same method called on each component controller.
 * 
 * @see ChassisController
 */
public class ChassisControllerGroup implements ChassisController {
	protected ChassisController[] controllers;

	public ChassisControllerGroup(ChassisController... controllers) {
		this.controllers = controllers;
	}

	@Override
	public double getX() {
		return Arrays.stream(controllers).mapToDouble((ctrlr) -> ctrlr.getX()).sum();
	}

	@Override
	public double getY() {
		return Arrays.stream(controllers).mapToDouble((ctrlr) -> ctrlr.getY()).sum();
	}

	@Override
	public double getTurnSpeed() {
		return Arrays.stream(controllers).mapToDouble((ctrlr) -> ctrlr.getTurnSpeed()).sum();
	}
}
