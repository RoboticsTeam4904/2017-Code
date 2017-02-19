package org.usfirst.frc4904.robot.autonomous.strategies;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPlaceGear extends CommandGroup {
	public AutonPlaceGear() {
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, 38.5, RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		RobotMap.Component.gearIO.setState(GearIO.GearState.OUTTAKE);
	}

}
