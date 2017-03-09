package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CalibrateCameraExposure extends Command {
	NetworkTable table;
	static double numCalibrations = 0;
	boolean didCalibrate = false;

	public CalibrateCameraExposure() {
		table = NetworkTable.getTable("autocalibrate");
		// NetworkTable.setIPAddress("10.49.4.2");
		// NetworkTable.setClientMode();
		// NetworkTable.setTeam(4904);
		// NetworkTable.initialize();
	}

	@Override
	protected void initialize() {
		CalibrateCameraExposure.numCalibrations = 0;
	}

	@Override
	protected void execute() {
		// System.out.println("num calibrations is " +
		// CalibrateCameraExposure3.numCalibrations);
		CalibrateCameraExposure.numCalibrations += 1;
		table.putNumber("autocalibrate", CalibrateCameraExposure.numCalibrations);
	}

	@Override
	protected boolean isFinished() {
		return table.getBoolean("didCalibrate", false);
	}
	// public static void main(String[] args) {
	// new CalibrateCameraExposure();
	// }
	//
	// public CalibrateCameraExposure() {
	// initialize();
	// while (!isFinished()) {
	// execute();
	// }
	// }
}
