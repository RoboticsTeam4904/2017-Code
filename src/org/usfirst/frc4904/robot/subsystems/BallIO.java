package org.usfirst.frc4904.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallIO extends Subsystem {
	public SpeedController topMotor;
	public SpeedController leftMotor;
	public SpeedController mainMotor;
	public DoubleSolenoid shifter;
	
	public BallIO(SpeedController topMotor, SpeedController leftMotor, SpeedController mainMotor, DoubleSolenoid shifter) {
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
