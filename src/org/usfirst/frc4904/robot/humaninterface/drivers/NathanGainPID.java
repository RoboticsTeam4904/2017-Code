package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.custom.MCChassisController;

public class NathanGainPID extends MCChassisController {
	public static final double MAXIMUM_DEGREES_PER_SECOND = 100; // TODO

	public NathanGainPID() {
		super(new NathanGain(), RobotMap.Component.navx, RobotMap.Component.chassisDriveStraightMC,
			NathanGainPID.MAXIMUM_DEGREES_PER_SECOND);
	}
}
