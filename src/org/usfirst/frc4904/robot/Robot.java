package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.drivers.DefaultDriver;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.sovereignty.PIDContainerOrchestrator;
import org.usfirst.frc4904.sovereignty.PIDContainerOrchestrator.PIDContainer.PIDValueModifier;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.commands.Noop;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	PIDContainerOrchestrator orchestrator = new PIDContainerOrchestrator();
	
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
		orchestrator.pushValues(new PIDValueModifier() {
			@Override
			public void pushValue(String key, double value) {
				SmartDashboard.putNumber(key, value);
			}
			
			@Override
			public double pullValue(String key) {
				return SmartDashboard.getNumber(key, 0.0);
			}
		});
	}
	
	@Override
	public void teleopInitialize() {
		teleopCommand = new Noop();
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
	public void disabledExecute() {
		
	}
	
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
	
	private static class SmartDashboardModifierImpl implements PIDValueModifier {
		
		@Override
		public void pushValue(String key, double value) {
			SmartDashboard.putNumber(key, value);
		}
		
		@Override
		public double pullValue(String key) {
			return SmartDashboard.getNumber(key);
		}
	}
}
