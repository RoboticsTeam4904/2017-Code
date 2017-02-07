package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearIO extends Subsystem {
	public Servo gearFlap;
	public DoubleSolenoid gearSlotOpener;

	public GearIO(Servo gearFlap, DoubleSolenoid gearSlotOpener) {
		this.gearFlap = gearFlap;
		this.gearSlotOpener = gearSlotOpener;
	}

	public static enum GearState {
		INTAKE(90, DoubleSolenoid.Value.kForward), OUTTAKE(0, DoubleSolenoid.Value.kReverse);
		public final double angle;
		public final DoubleSolenoid.Value value;// the architecture allowing the enum states to have values
		private GearState(double angle, DoubleSolenoid.Value value) {

			this.angle = angle;
			this.value = value;
		}
	}

	public void setGear(GearState state) {
		gearSlotOpener.set(state.value);
		gearFlap.setAngle(state.angle);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
