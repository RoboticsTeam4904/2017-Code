package org.usfirst.frc4904.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallIntakeOuttake extends Subsystem {
	SpeedController topMotor;
	SpeedController leftMotor;
	SpeedController mainMotor;
	DoubleSolenoid shifter;
	
	public BallIntakeOuttake(SpeedController topMotor, SpeedController leftMotor, SpeedController mainMotor, DoubleSolenoid shifter) {
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
