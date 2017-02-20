package org.usfirst.frc4904.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CalibrateCameraExposure2 extends Command {

	NetworkTable iWishThisWasASocket;
	static double numCalibrations = 0;

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void initialize() {
		iWishThisWasASocket = NetworkTable.getTable("autocalibration");
	}

	@Override
	protected void execute() {
		CalibrateCameraExposure2.numCalibrations += 1;
		iWishThisWasASocket.putNumber("autocalibrate", CalibrateCameraExposure2.numCalibrations);
	}

	public static void main(String[] args) {
		new CalibrateCameraExposure2();
	}

	public CalibrateCameraExposure2() {
		execute();
	}

}
