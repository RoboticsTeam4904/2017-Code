package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.KittenCommand;
import org.usfirst.frc4904.standard.commands.RunFor;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BoilerAlign extends CommandGroup {
	protected static final int ALIGNMENT_CYCLES = 3;
	protected static final int MAX_CYCLE_TIME_SECONDS = 1;

	public BoilerAlign() {
		// Stage 1: center on the boiler using the first set of LiDAR values
		for (int i = 0; i < BoilerAlign.ALIGNMENT_CYCLES; i++) {
			addSequential(new WaitCommand(0.25));
			addSequential(
				new RunFor(new LIDARTurn(RobotMap.Component.lidar.getLidarSensor1()), BoilerAlign.MAX_CYCLE_TIME_SECONDS));
			addSequential(
				new RunFor(new LIDARDrive(RobotMap.Component.lidar.getLidarSensor1()), BoilerAlign.MAX_CYCLE_TIME_SECONDS));
		}
		// Stage 2: drive to the boiler using the second set of LiDAR values
		for (int i = 0; i < BoilerAlign.ALIGNMENT_CYCLES; i++) {
			addSequential(new WaitCommand(0.25));
			addSequential(
				new RunFor(new LIDARTurn(RobotMap.Component.lidar.getLidarSensor2()), BoilerAlign.MAX_CYCLE_TIME_SECONDS));
			addSequential(
				new RunFor(new LIDARDrive(RobotMap.Component.lidar.getLidarSensor2()), BoilerAlign.MAX_CYCLE_TIME_SECONDS));
		}
		addSequential(new KittenCommand("Done boiler aligning (I did my best)", LogKitten.LEVEL_VERBOSE));
	}
}
