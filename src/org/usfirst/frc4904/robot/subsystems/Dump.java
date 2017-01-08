package org.usfirst.frc4904.robot.subsystems;


import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Dump extends Subsystem {
	
	SpeedController Elevator;
	SpeedController Outtake;
	
	public Dump(SpeedController e, SpeedController o) { // Public Dump - Let Everyone see it!
		Elevator = e;
		Outtake = o;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public void beingSpinOuttake() {
		Elevator.set(1.0);
	}
	
	public void stopSpinOuttake() {
		Elevator.set(0.0);
	}
}
