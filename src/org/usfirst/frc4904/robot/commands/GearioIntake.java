package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import edu.wpi.first.wpilibj.command.Command;

public class GearioIntake extends GearioSet {
	protected final Command rampCommand = new RampSet(GearIO.RampState.EXTENDED);

	public GearioIntake() {
		super(GearIO.GearState.INTAKE);
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (RobotMap.Component.gearIO.isNotOverridden()) {
			rampCommand.start();
		}
	}
}
