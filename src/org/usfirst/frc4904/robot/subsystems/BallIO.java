package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallIO extends Subsystem {
	public final Motor topMotor;
	public final Motor leftMotor;
	public final Motor mainMotor;
	public final DoubleSolenoid shifter;
	
	public BallIO(Motor topMotor, Motor leftMotor, Motor mainMotor, DoubleSolenoid shifter) {
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
