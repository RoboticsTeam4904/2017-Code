package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisShift;
import org.usfirst.frc4904.standard.humaninput.Driver;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifier;
import edu.wpi.first.wpilibj.command.Command;

strictfp public class NathanGain extends Driver {
	public static final double SPEED_GAIN = 1;
	public static final double TURN_GAIN = 1;
	public static final double SPEED_EXP = 2;
	public static final double TURN_EXP = 2;
	public static final double Y_SPEED_SCALE = 1;
	public static final double TURN_SPEED_SCALE = 1;
	private final FineModifier modifier = new FineModifier();
	
	public NathanGain() {
		super("NathanGain");
	}
	
	protected double scaleGain(double input, double gain, double exp) {
		return Math.pow(input, exp) * gain * Math.signum(input);
	}
	
	@Override
	public void bindCommands() {
		RobotMap.Component.xbox.y.toggleWhenPressed(new EnableFineModifier(modifier));
		RobotMap.Component.xbox.a.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.DOWN));
		RobotMap.Component.xbox.b.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.UP));
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		double rawSpeed = RobotMap.Component.xbox.rt.getX() - RobotMap.Component.xbox.lt.getX();
		double speed = scaleGain(modifier.modify(rawSpeed), NathanGain.SPEED_GAIN, NathanGain.SPEED_EXP) * NathanGain.Y_SPEED_SCALE;
		return speed;
	}
	
	@Override
	public double getTurnSpeed() {
		double rawTurnSpeed = RobotMap.Component.xbox.leftStick.getX();
		double turnSpeed = scaleGain(modifier.modify(rawTurnSpeed), NathanGain.TURN_GAIN, NathanGain.TURN_EXP) * NathanGain.TURN_SPEED_SCALE;
		return turnSpeed;
	}
	
	public static class FineModifier implements SpeedModifier {
		
		private boolean fineEnabled = false;
		
		@Override
		public double modify(double speed) {
			if (fineEnabled) {
				return speed / 3;
			}
			return speed;
		}
		
		public void setFineControl(boolean fine) {
			fineEnabled = fine;
		}
		
		public boolean getFineControl() {
			return fineEnabled;
		}
	}
	
	private class EnableFineModifier extends Command {
		
		private final FineModifier modifier;
		
		public EnableFineModifier(FineModifier modifier) {
			this.modifier = modifier;
		}
		
		@Override
		protected void initialize() {
			modifier.setFineControl(true);
		}
		
		@Override
		protected boolean isFinished() {
			return false;
		}
		
		@Override
		protected void interrupted() {
			modifier.setFineControl(false);
		}
	}
}
