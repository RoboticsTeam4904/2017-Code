package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.sovereignty.PIDContainerOrchestrator;
import org.usfirst.frc4904.sovereignty.PIDContainerOrchestrator.PIDContainer;
import org.usfirst.frc4904.sovereignty.PIDContainerOrchestrator.PIDContainer.PIDValueType;
import org.usfirst.frc4904.sovereignty.PIDContainerWrapper;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.custom.ChassisController;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAlign extends CommandGroup implements ChassisController {
	// TODO: Adjust formatter to make this readable
	public static PIDContainer angleContainer = PIDContainerOrchestrator.getInstance()//
		.createContainer("GearAlign", true) //
		.set(PIDValueType.P, -0.0015) //
		.set(PIDValueType.I, -1.0E-5) //
		.set(PIDValueType.D, 0.015) //
		.set(PIDValueType.SETPOINT, 320) //
		.set(PIDValueType.TOLERANCE, 0.001);
	protected PIDContainerWrapper pidWrapper;
	protected boolean onAngle = false;
	private ChassisMove chassisMove;
	
	public GearAlign(AligningCamera camera) {
		pidWrapper = new PIDContainerWrapper(GearAlign.angleContainer, camera);
		pidWrapper.updateController();
		pidWrapper.getController().setOutputRange(-1, 1);
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
		double pidGet = pidWrapper.getController().get();
		onAngle = pidWrapper.getController().onTarget();
		return pidGet;
	}
	
	@Override
	public void initialize() {
		chassisMove = new ChassisMove(RobotMap.Component.chassis, this);
		chassisMove.start();
		pidWrapper.getController().enable();
		pidWrapper.updateController();
	}
	
	@Override
	public void execute() {
		pidWrapper.updateController();
	}
	
	@Override
	public boolean isFinished() {
		return onAngle;
	}
	
	@Override
	public void end() {
		chassisMove.cancel();
		pidWrapper.getController().reset();
		onAngle = false;
	}
	
	@Override
	public void interrupted() {
		end();
	}
}
