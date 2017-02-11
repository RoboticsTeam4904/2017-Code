package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.sovereignty.AligningSystem;
import org.usfirst.frc4904.sovereignty.Trimmable;
import org.usfirst.frc4904.sovereignty.TrimmablePIDController;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.custom.ChassisController;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAlign extends CommandGroup implements ChassisController, Trimmable {
	// PID Constants
	public static double ANGLE_P = -0.0015;
	public static double ANGLE_I = -1.0E-5;
	public static double ANGLE_D = 0.015;
	public static final double ANGLE_SETPOINT = 320;
	public static final double ANGLE_TOLERANCE = 0.001;
	protected AligningSystem alignSystem;
	protected TrimmablePIDController pidController;
	protected boolean onAngle = false;
	public static boolean HELLA = false;
	private ChassisMove chassisMove;

	public GearAlign() {
		alignSystem = new AligningSystem(RobotMap.Component.alignCamera, RobotMap.Component.navx);
		pidController = new TrimmablePIDController(GearAlign.ANGLE_P, GearAlign.ANGLE_I, GearAlign.ANGLE_D, alignSystem);
		pidController.setAbsoluteTolerance(GearAlign.ANGLE_TOLERANCE);
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
		double pidGet = pidController.get();
		onAngle = pidController.onTarget();
		return pidGet;
	}

	@Override
	public void initialize() {
		alignSystem.start();
		chassisMove = new ChassisMove(RobotMap.Component.chassis, this);
		chassisMove.start();
		pidController.enable();
		pidController.setSetpoint(GearAlign.ANGLE_SETPOINT); // Potentially change this to be the horizontal resolution of the camera / 2
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
		alignSystem.cancel();
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

	public boolean isAligned() {
		Double value = alignSystem.getDegrees();
		return GearAlign.HELLA;
		// if (value == null || Math.abs(value) > GearAlign.ANGLE_TOLERANCE) {
		// return false;
		// }
		// return true;
	}
}
