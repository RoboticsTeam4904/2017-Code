package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.commands.FloorioTransport;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FloorIO extends Subsystem {
	public final Motor roller;
	public final DoubleSolenoid piston;
	public static final double INTAKE_SPEED = 0.75;
	public static final double OUTTAKE_SPEED = -0.75;
	public static final DoubleSolenoid.Value LOWERED = DoubleSolenoid.Value.kReverse;
	public static final DoubleSolenoid.Value RAISED = DoubleSolenoid.Value.kForward;
	public static final double OUTTAKE_ROLLER_DELAY_SECONDS = 0.1;

	public FloorIO(Motor roller, DoubleSolenoid piston) {
		this.roller = roller;
		this.piston = piston;
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new FloorioTransport());
	}
}
