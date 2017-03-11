package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.autonomous.strategies.AutonGearBoilerPeg;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonGearCenterPeg;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonGearLoadPeg;
import org.usfirst.frc4904.robot.commands.MatchInformer;
import org.usfirst.frc4904.robot.commands.MatchRecorder;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	MatchRecorder logger = new MatchRecorder();
	MatchInformer matchConfigBroadcast = new MatchInformer();

	@Override
	public void initialize() {
		// Configure autonomous command chooser
		// autoChooser.addDefault(new ChassisIdle(RobotMap.Component.chassis));
		autoChooser.addObject(new AutonGearCenterPeg());
		autoChooser.addObject(new AutonGearLoadPeg(false));
		autoChooser.addObject(new AutonGearBoilerPeg(false));
		autoChooser.addObject(new AutonGearLoadPeg(true));
		// autoChooser.addDefault(new AutonGearBoilerPeg(true));
		autoChooser.addDefault(new ChassisMoveDistance(RobotMap.Component.chassis, 3 * 12, RobotMap.Component.chassisDriveMC));
		// Configure driver chooser
		driverChooser.addDefault(new NathanGain());
		// Configure operator chooser
		operatorChooser.addDefault(new DefaultOperator());
		matchConfigBroadcast.start();
		RobotMap.Component.navx.zeroYaw();
		logger.start();
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
		RobotMap.Component.chassisDriveMC.setPID(SmartDashboard.getNumber("P", 0), SmartDashboard.getNumber("I", 0),
			SmartDashboard.getNumber("D", 0));
		LogKitten.wtf("P: " + RobotMap.Component.chassisDriveMC.getP());
		LogKitten.wtf("I: " + RobotMap.Component.chassisDriveMC.getI());
		LogKitten.wtf("D: " + RobotMap.Component.chassisDriveMC.getD());
		LogKitten.wtf("ERR: " + RobotMap.Component.chassisDriveMC.getError());
		SmartDashboard.putNumber("Error", RobotMap.Component.chassisDriveMC.getError());
		SmartDashboard.putNumber("Sensor", RobotMap.Component.chassisDriveMC.getSensorValue());
		SmartDashboard.putNumber("Setpoint", RobotMap.Component.chassisDriveMC.getSetpoint());
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
