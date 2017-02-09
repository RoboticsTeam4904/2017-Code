package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.commands.CANInformer;
import org.usfirst.frc4904.robot.humaninterface.drivers.JoystickControl;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.drivers.PureStick;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	CANInformer matchConfigBroadcast = new CANInformer(RobotMap.Port.CAN.matchConfigBroadcast, this::matchConfigurationCANData);

	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addDefault(new ChassisIdle(RobotMap.Component.chassis));
		// Configure driver command chooser
		driverChooser.addDefault(new NathanGain());
		driverChooser.addObject(new NathanGain());
		driverChooser.addObject(new JoystickControl());
		driverChooser.addObject(new PureStick());
		RobotMap.Component.navx.zeroYaw();
		operatorChooser.addDefault(new DefaultOperator());
		matchConfigBroadcast.start();
	}

	@Override
	public void teleopInitialize() {
		// teleopCommand = new Noop();
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

	byte[] matchConfigurationCANData() {
		// Game time will generally be in the 0-150 second range.
		// All Java bytes are signed, so anything >127 will overflow into the negatives.
		// However, the Teensy can read them as unsigned, so we're all good.
		byte gameTime = (byte) Math.round(DriverStation.getInstance().getMatchTime());
		byte gameMode = -1; // default/unknown
		if (DriverStation.getInstance().isDisabled()) {
			gameMode = 0;
		}
		if (DriverStation.getInstance().isAutonomous()) {
			gameMode = 1;
		}
		if (DriverStation.getInstance().isEnabled()) {
			gameMode = 2;
		}
		if (DriverStation.getInstance().isTest()) {
			gameMode = 3;
		}
		return new byte[] {(byte) DriverStation.getInstance().getAlliance().ordinal(),
				(byte) DriverStation.getInstance().getLocation(), gameMode, gameTime,
				0, 0, 0,
				0};
	}

	void putSDSubsystemSummary() {
		String summary = "";
		for (Subsystem subsystem : RobotMap.Component.mainSubsystems) {
			summary += "{" + subsystem.getName() + "} running command {" + subsystem.getCurrentCommand() + "}\n";
		}
		SmartDashboard.putString(SmartDashboardKey.SUBSYSTEM_SUMMARY.key, summary);
	}
}
