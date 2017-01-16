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
		gearSlotOpener.set(DoubleSolenoid.Value.kForward);// closes gear stuff
		gearFlap.setAngle(90);// might be inaccurate--will test
	}
	
	public void OuttakeGear() {
		gearFlap.setAngle(0);// closes gearFlap
		gearSlotOpener.set(DoubleSolenoid.Value.kReverse);// opens gear holder
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
