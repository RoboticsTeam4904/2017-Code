package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.commands.LidarTurner;
import org.usfirst.frc4904.robot.humaninterface.drivers.DefaultDriver;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.robot.subsystems.Lidar;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.commands.Noop;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	Command LidarCommand;
	
	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addDefault(new Noop());
		// Configure driver command chooser
		driverChooser.addDefault(new DefaultDriver());
		// Configure operator command chooser
		operatorChooser.addDefault(new DefaultOperator());
		// Initialize SmartDashboard display values
		SmartDashboard.putNumber("LIDAR_P", Lidar.LIDAR_TURN_P);
		SmartDashboard.putNumber("LIDAR_I", Lidar.LIDAR_TURN_I);
		SmartDashboard.putNumber("LIDAR_D", Lidar.LIDAR_TURN_D);
	}
	
	@Override
	public void teleopInitialize() {
		teleopCommand = new Noop();
		LidarCommand = new LidarTurner();
		LidarCommand.start();
		RobotMap.Component.lidarMC.reset();
		Lidar.LIDAR_TURN_P = SmartDashboard.getNumber("LIDAR_P", Lidar.LIDAR_TURN_P);
		Lidar.LIDAR_TURN_I = SmartDashboard.getNumber("LIDAR_I", Lidar.LIDAR_TURN_I);
		Lidar.LIDAR_TURN_D = SmartDashboard.getNumber("LIDAR_D", Lidar.LIDAR_TURN_D);
		RobotMap.Component.lidarMC.setPID(Lidar.LIDAR_TURN_P, Lidar.LIDAR_TURN_I, Lidar.LIDAR_TURN_D);
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopExecute() {}
	
	@Override
	public void autonomousInitialize() {}
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousExecute() {}
	
	/**
	 * This function is called periodically in every robot mode
	 */
	@Override
	public void alwaysExecute() {
		putSDSubsystemSummary();
	}
	
	@Override
	public void disabledInitialize() {}
	
	@Override
	public void disabledExecute() {}
	
	@Override
	public void testInitialize() {}
	
	@Override
	public void testExecute() {}
	
	void putSDSubsystemSummary() {
		String summary = "";
		for (Subsystem subsystem : RobotMap.Component.mainSubsystems) {
			summary += "{" + subsystem.getName() + "} running command {" + subsystem.getCurrentCommand() + "}\n";
		}
		SmartDashboard.putString(SmartDashboardKey.SUBSYSTEM_SUMMARY.key, summary);
	}
}
