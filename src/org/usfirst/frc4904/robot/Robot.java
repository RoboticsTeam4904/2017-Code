package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.autonomous.strategies.AutonCrossBaselineTime;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonGearBoilerPegTimeBlue;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonGearBoilerPegTimeRed;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonGearCenterPegTime;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonGearLoadPegTimeBlue;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonGearLoadPegTimeRed;
import org.usfirst.frc4904.robot.commands.LIDARTurnbaugh;
import org.usfirst.frc4904.robot.commands.MatchInformer;
import org.usfirst.frc4904.robot.commands.MatchRecorder;
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
	MatchRecorder logger = new MatchRecorder();
	MatchInformer matchConfigBroadcast = new MatchInformer();

	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addDefault(new ChassisIdle(RobotMap.Component.chassis));
		autoChooser.addObject(new AutonGearCenterPegTime());
		autoChooser.addObject(new AutonGearLoadPegTimeBlue());
		autoChooser.addObject(new AutonGearLoadPegTimeRed());
		autoChooser.addObject(new AutonGearBoilerPegTimeBlue());
		autoChooser.addObject(new AutonGearBoilerPegTimeRed());
		autoChooser.addObject(new AutonCrossBaselineTime());
		// Configure driver chooser
		driverChooser.addDefault(new NathanGain());
		// Configure operator chooser
		operatorChooser.addDefault(new DefaultOperator());
		operatorChooser.addObject(new BillyOperator());
		matchConfigBroadcast.start();
		RobotMap.Component.navx.zeroYaw();
		logger.start();
	}

	@Override
	public void teleopInitialize() {
		teleopCommand = new ChassisMove(RobotMap.Component.chassis, driverChooser.getSelected());
		teleopCommand.start();
		(new LIDARTurnbaugh()).start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopExecute() {}

	@Override
	public void autonomousInitialize() {
		RobotMap.Component.navx.zeroYaw();
		(new LIDARTurnbaugh()).start();
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
		SmartDashboard.putNumber(SmartDashboardKey.BOILER_CENTER_ANGLE.key, RobotMap.Component.lidar.getBoilerCenterAngle());
		SmartDashboard.putNumber(SmartDashboardKey.GEAR_PEG_CENTER_ANGLE.key, RobotMap.Component.gearAlignCamera.getDegrees());
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
