package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.CapSpeedModifier;
import edu.wpi.first.wpilibj.SpeedController;

public class Climber extends Motor {
	public Climber(SpeedController climbMotor) {
		super(new CapSpeedModifier(0, 1), climbMotor); // Really REALLY don't give this motor any nonpositive values
	}
}
