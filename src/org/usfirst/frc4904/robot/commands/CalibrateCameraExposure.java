package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.SmartDashboardKey;
import org.usfirst.frc4904.standard.LogKitten;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CalibrateCameraExposure extends Command {
	protected final NetworkTable table;

	public CalibrateCameraExposure() {
		table = NetworkTable.getTable("Vision");
	}

	@Override
	protected void initialize() {
		table.putBoolean(SmartDashboardKey.VISION_AUTOCALIBRATION.key, true);
		table.putBoolean(SmartDashboardKey.VISION_AUTOCALIBRATION_COMPLETE.key, false);
	}

	@Override
	protected boolean isFinished() {
		return table.getBoolean(SmartDashboardKey.VISION_AUTOCALIBRATION_COMPLETE.key, false);
	}

	@Override
	protected void end() {
		LogKitten.w("Autocalibration complete");
	}
}
