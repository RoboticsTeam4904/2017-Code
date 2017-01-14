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
	
	public void beingSpinOuttake() {
		outtake.set(1.0);
	}
	
	public void stopSpinOuttake() {
		outtake.set(0.0);
	}
}
