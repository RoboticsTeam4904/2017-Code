package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;

public class VisionTurn extends ChassisTurn {
	AligningCamera camera;

	public VisionTurn(AligningCamera camera) {
		super(RobotMap.Component.chassis, 0.0, RobotMap.Component.navx, RobotMap.Component.chassisTurnMC);
		this.camera = camera;
	}

	@Override
	protected void initialize() {
		super.initialize();
		initialAngle -= camera.getDegrees();
	}

	@Override
	protected boolean isFinished() {
		if (!camera.trustable()) {
			LogKitten.w("Camera not reliable right now");
			return true;
		}
		return super.isFinished() || !camera.trustable();
	}
}
