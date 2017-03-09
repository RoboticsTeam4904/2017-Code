package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CalibrateCameraExposure extends Command {
	protected final NetworkTable table;
	protected double numCalibrations;

	public CalibrateCameraExposure() {
		table = NetworkTable.getTable("autocalibrate");
	}

	@Override
	protected void initialize() {
		numCalibrations = 0;
	}

	@Override
	protected void execute() {
		table.putNumber("autocalibrate", ++numCalibrations);
	}

	@Override
	protected boolean isFinished() {
		return table.getBoolean("didCalibrate", false);
	}
}
