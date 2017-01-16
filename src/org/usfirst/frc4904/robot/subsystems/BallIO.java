package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallIO extends Subsystem {
	public Motor topMotor;
	public Motor leftMotor;
	public Motor mainMotor;
	public DoubleSolenoid shifter;
	
	public BallIO(Motor topMotor, Motor leftMotor, Motor mainMotor, DoubleSolenoid shifter) {
		this.topMotor = topMotor;
		this.leftMotor = leftMotor;
		this.mainMotor = mainMotor;
		this.shifter = shifter;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
