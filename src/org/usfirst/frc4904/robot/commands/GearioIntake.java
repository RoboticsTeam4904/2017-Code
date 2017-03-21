package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.standard.commands.OverridableCommandGroup;

public class GearioIntake extends OverridableCommandGroup {
	public GearioIntake() {
		super("GearioIntake", RobotMap.Component.gearIO);
		addParallelUnlessOverridden(new RampSet(GearIO.RampState.EXTENDED));
		addParallel(new GearioSet(GearIO.GearState.INTAKE));
	}
}
