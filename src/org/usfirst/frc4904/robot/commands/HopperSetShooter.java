package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.Hopper;

public class HopperSetShooter extends HopperSet {
	public HopperSetShooter() {
		super(Hopper.HopperState.SHOOTER);
	}
}
