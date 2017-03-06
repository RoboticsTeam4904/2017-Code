package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.commands.CANInformer;
import org.usfirst.frc4904.robot.commands.MatchInformer;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.BillyOperator;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	CANInformer matchConfigBroadcast = new MatchInformer();

	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addDefault(new ChassisIdle(RobotMap.Component.chassis));
		// Configure driver chooser
		driverChooser.addDefault(new NathanGain());
		// Configure operator chooser
		operatorChooser.addDefault(new DefaultOperator());
		operatorChooser.addObject(new BillyOperator());
		matchConfigBroadcast.start();
		RobotMap.Component.navx.zeroYaw();
	}

	@Override
	public void teleopInitialize() {
		teleopCommand = new ChassisMove(RobotMap.Component.chassis, driverChooser.getSelected());
		teleopCommand.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopExecute() {}

	@Override
	public void autonomousInitialize() {
		RobotMap.Component.navx.zeroYaw();
	}

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
