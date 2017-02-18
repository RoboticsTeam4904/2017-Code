package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class GearioIntake extends GearSet {
	public GearioIntake() {
		super(GearIO.GearState.INTAKE);
	}

	@Override
	protected void initialize() {
		super.initialize();
		RobotMap.Component.gearIO.setRampState(DoubleSolenoid.Value.kForward);
	}
}
