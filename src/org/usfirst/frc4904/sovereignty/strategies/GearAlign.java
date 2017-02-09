package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.sovereignty.PIDContainer;
import org.usfirst.frc4904.sovereignty.PIDContainer.PIDValueType;
import org.usfirst.frc4904.sovereignty.PIDContainerWrapper;
import org.usfirst.frc4904.sovereignty.Trimmable;
import org.usfirst.frc4904.sovereignty.TrimmablePIDController;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.custom.ChassisController;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAlign extends CommandGroup implements ChassisController, Trimmable {
	public static PIDContainer angleContainer = new PIDContainer("GearAlign")
		.set(PIDValueType.P, -0.0015)
		.set(PIDValueType.I, -1.0E-5)
		.set(PIDValueType.D, 0.015)
		.set(PIDValueType.SETPOINT, 320)
		.set(PIDValueType.TOLERANCE, 0.001);
	protected PIDContainerWrapper pidWrapper;
	protected boolean onAngle = false;
	private final TrimmablePIDController pidController;
	private ChassisMove chassisMove;

	public GearAlign(AligningCamera camera) {
		pidController = new TrimmablePIDController(camera);
		pidWrapper = new PIDContainerWrapper(GearAlign.angleContainer, pidController);
		pidWrapper.updateController();
		pidController.setOutputRange(-1, 1);
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
		pidController.enable();
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
		pidController.reset();
		onAngle = false;
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	public void setTrimIncrement(double increment) {
		pidController.setTrimIncrement(increment);
	}

	@Override
	public double getTrimIncrement() {
		return pidController.getTrimIncrement();
	}

	@Override
	public void setTrim(double trim) {
		pidController.setTrim(trim);
	}

	@Override
	public double getTrim() {
		return pidController.getTrim();
	}
}
