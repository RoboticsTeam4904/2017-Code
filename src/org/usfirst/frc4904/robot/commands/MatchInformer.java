package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.DriverStation;

public class MatchInformer extends CANInformer {
	public MatchInformer() {
		super(RobotMap.Port.CAN.matchConfigBroadcast);
	}

	@Override
	protected byte[] getCANMessage() {
		// Game time will generally be in the 0-150 second range.
		// All Java bytes are signed, so anything >127 will overflow into the negatives.
		// However, the Teensy can read them as unsigned, so we're all good.
		long roundedGameTime = Math.round(DriverStation.getInstance().getMatchTime());
		byte gameTime = (byte) (roundedGameTime & 0xFF);
		byte gameMode = -1; // default/unknown
		if (DriverStation.getInstance().isDisabled()) {
			gameMode = 0;
		}
		if (DriverStation.getInstance().isAutonomous() && DriverStation.getInstance().isEnabled()) {
			gameMode = 1;
		}
		if (DriverStation.getInstance().isOperatorControl() && DriverStation.getInstance().isEnabled()) {
			gameMode = 2;
		}
		if (DriverStation.getInstance().isTest() && DriverStation.getInstance().isEnabled()) {
			gameMode = 3;
		}
		return new byte[] {(byte) DriverStation.getInstance().getAlliance().ordinal(),
				(byte) DriverStation.getInstance().getLocation(), gameMode, gameTime,
				0, 0, 0, 0};
	}
}
