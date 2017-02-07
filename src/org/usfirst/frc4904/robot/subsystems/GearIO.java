package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearIO extends Subsystem {
	public final Servo gearFlap;
	public final DoubleSolenoid gearSlotOpener;

	public GearIO(Servo gearFlap, DoubleSolenoid gearSlotOpener) {
		this.gearFlap = gearFlap;
		this.gearSlotOpener = gearSlotOpener;
	}

	public static enum GearState {
		INTAKE(90, DoubleSolenoid.Value.kForward), OUTTAKE(0, DoubleSolenoid.Value.kReverse);
		private final double angle;
		private final DoubleSolenoid.Value solenoidValue;

		private GearState(double angle, DoubleSolenoid.Value solenoidValue) {
			this.angle = angle;
			this.solenoidValue = solenoidValue;
		}

		public double getAngle() {
			return angle;
		}

		public DoubleSolenoid.Value getSolenoidValue() {
			return solenoidValue;
		}
	}

	public void setGear(GearState state) {
		gearSlotOpener.set(state.getSolenoidValue());
		gearFlap.setAngle(state.getAngle());
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}