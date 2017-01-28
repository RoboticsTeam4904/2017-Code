package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallIO extends Subsystem {
	public final Motor topMotor;
	public final Motor leftMotor;
	public final Motor mainMotor;
	public final Servo shifter;
	public static final double outtakeAngle = 71.03; // for now
	public static final double intakeAngle = 0; // for now
	
	public BallIO(Motor topMotor, Motor leftMotor, Motor mainMotor, Servo shifter) {
		this.topMotor = topMotor;
		this.leftMotor = leftMotor;
		this.mainMotor = mainMotor;
		this.shifter = shifter;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
