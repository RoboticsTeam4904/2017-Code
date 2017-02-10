package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.commands.AutoShifter;
import org.usfirst.frc4904.robot.humaninterface.drivers.DefaultDriver;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.Noop;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	Command AutoShifter;
	
	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addDefault(new Noop());
		// Configure driver command chooser
		driverChooser.addDefault(new DefaultDriver());
		// Configure operator command chooser
		operatorChooser.addDefault(new DefaultOperator());
		// Initialize SmartDashboard display values
		// SmartDashboard.putNumber(SmartDashboardKey.EXAMPLE.key, 0);
	}
	
	@Override
	public void teleopInitialize() {
		teleopCommand = new Noop();
		AutoShifter = new AutoShifter();
		AutoShifter.start();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopExecute() {
		RobotMap.Component.teensyStick.button4.whenPressed(new Kill(AutoShifter));
		RobotMap.Component.teensyStick.button4.whenReleased(AutoShifter);
	}
	
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
