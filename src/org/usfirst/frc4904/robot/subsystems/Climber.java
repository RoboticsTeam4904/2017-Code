package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.SpeedController;

public class Climber extends Motor {
	public static final double CLIMBER_TARGET_SPEED = 0.65;
	
	public Climber(SpeedController climbMotor) {
		super(climbMotor);
	}
}
