package org.usfirst.frc4904.robot.autonomous;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Lidar;
import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.PositionSensorMotor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AutonSubsystem extends Subsystem {
	public PositionSensorMotor leftWheel;
	public PositionSensorMotor rightWheel;
	public Lidar lidar;
	
	public AutonSubsystem() {
		leftWheel = RobotMap.Component.leftWheel;
		rightWheel = RobotMap.Component.rightWheel;
		lidar = RobotMap.Component.lidar;
	}
	
	public enum ChassisAlliance {
		RED(1), BLUE(-1);
		public final int value;
		
		private ChassisAlliance(int value) {
			this.value = value;
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
