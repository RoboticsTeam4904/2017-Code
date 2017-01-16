package org.usfirst.frc4904.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntakeOuttake extends Subsystem {
	public Servo gearFlap;
	public DoubleSolenoid gearSlotOpener;
	
	public GearIntakeOuttake(Servo gearFlap, DoubleSolenoid gearSlotOpener) {
		this.gearFlap = gearFlap;
		this.gearSlotOpener = gearSlotOpener;
	}
	
	public void IntakeGear() {
		gearSlotOpener.set(DoubleSolenoid.Value.kForward);
		gearFlap.setAngle(90);
	}
	
	public void OuttakeGear() {
		gearFlap.setAngle(0);
		gearSlotOpener.set(DoubleSolenoid.Value.kReverse);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
