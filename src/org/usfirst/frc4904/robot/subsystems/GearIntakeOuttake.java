package org.usfirst.frc4904.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntakeOuttake extends Subsystem {
	Servo gearFlap;
	DoubleSolenoid gearSlotOpener;
	
	public GearIntakeOuttake(Servo gearFlap, DoubleSolenoid gearSlotOpener) {
		this.gearFlap = gearFlap;
		this.gearSlotOpener = gearSlotOpener;
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
