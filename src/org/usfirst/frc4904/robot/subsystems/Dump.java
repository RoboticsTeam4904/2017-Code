package org.usfirst.frc4904.robot.subsystems;


import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Dump extends Subsystem {
	
	SpeedController elevator;
	SpeedController outtake;
	
	public Dump(SpeedController elevator, SpeedController outtake) { // Public Dump - Let Everyone see it!
		this.elevator = elevator;
		this.outtake = outtake;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public void beingSpinOuttake(double spinSpeed) {
		outtake.set(spinSpeed);
	}
	
	public void stopSpinOuttake() {
		outtake.set(0.0);
	}
	
	public void spinElevator(double spinSpeed) {
		elevator.set(spinSpeed);
	}
	
	public void stopSpinElevator() {
		elevator.set(0.0);
	}
}
