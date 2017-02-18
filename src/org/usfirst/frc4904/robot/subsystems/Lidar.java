package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.LIDARTurnbaugh;
import org.usfirst.frc4904.standard.subsystems.motor.VelocitySensorMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.CapSpeedModifier;
import edu.wpi.first.wpilibj.SpeedController;

public class LIDAR extends VelocitySensorMotor {
	public static double TURN_P = 0.000006; // WIP
	public static double TURN_I = 0.00000000605; // WIP
	public static double TURN_D = 0; // WIP
	public static final double TURN_F = 0.00000138;

	public LIDAR(SpeedController motor) {
		super("LIDAR", false, new CapSpeedModifier(0.1, 0.4), RobotMap.Component.lidarMC, motor);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LIDARTurnbaugh());
	}
}
