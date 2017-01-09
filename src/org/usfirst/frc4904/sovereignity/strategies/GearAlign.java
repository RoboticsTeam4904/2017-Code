package org.usfirst.frc4904.sovereignity.strategies;


import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.sovereignity.TheRobot;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.custom.ChassisController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAlign extends CommandGroup implements ChassisController {
	
	protected TheRobot robot;
	protected AligningCamera camera;
	protected CustomPIDController pidController;
	protected static final double angleP = 0;
	protected static final double angleI = 0;
	protected static final double angleD = 0;
	protected boolean onAngle = false;
	private ChassisMove chassisMove;
	
	public GearAlign(TheRobot robot, AligningCamera camera) {
		this.robot = robot;
		this.camera = camera;
		pidController = new CustomPIDController(GearAlign.angleP, GearAlign.angleI, GearAlign.angleD, camera);
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		return 0;
	}
	
	@Override
	public double getTurnSpeed() {
		double pidGet = pidController.get();
		if (camera.isVisible()) {
			onAngle = pidController.onTarget();
			return pidGet;
		}
		return 0;
	}
	
	@Override
	public void initialize() {
		chassisMove = new ChassisMove(robot.chassis, this);
		chassisMove.start();
		pidController.enable();
		pidController.setSetpoint(0); // Potentially change this to be the horizontal resolution of the camera / 2
	}
	
	@Override
	public void execute() {}
	
	@Override
	public boolean isFinished() {
		return onAngle;
	}
	
	@Override
	public void end() {
		chassisMove.cancel();
		pidController.reset();
		onAngle = false;
	}
	
	@Override
	public void interrupted() {
		end();
	}
}
