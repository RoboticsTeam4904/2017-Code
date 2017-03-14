package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.autonomous.commands.GearBoilerPegApproach;
import org.usfirst.frc4904.robot.autonomous.commands.GearCenterPegApproach;
import org.usfirst.frc4904.robot.autonomous.commands.GearLoadPegApproach;
import org.usfirst.frc4904.robot.autonomous.commands.GearPegWiggleAndWithdraw;
import org.usfirst.frc4904.robot.commands.MatchInformer;
import org.usfirst.frc4904.robot.commands.MatchRecorder;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.BillyOperator;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.commands.Noop;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.custom.CommandSendableChooser;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	MatchRecorder logger = new MatchRecorder();
	MatchInformer matchConfigBroadcast = new MatchInformer();
	protected final CommandSendableChooser autoApproachChooser = new CommandSendableChooser();
	protected final CommandSendableChooser autoRealignChooser = new CommandSendableChooser();
	protected final CommandSendableChooser autoFinishChooser = new CommandSendableChooser();

	@Override
	public void initialize() {
		// Configure autonomous command choosers
		autoApproachChooser.addDefault(new Noop());
		autoApproachChooser.addObject(new GearCenterPegApproach());
		autoApproachChooser.addObject(new GearLoadPegApproach(true));
		autoApproachChooser.addObject(new GearLoadPegApproach(false));
		autoApproachChooser.addObject(new GearBoilerPegApproach(true));
		autoApproachChooser.addObject(new GearBoilerPegApproach(false));
		autoRealignChooser.addDefault(new Noop());
		autoRealignChooser.addObject(new GearAlign());
		autoFinishChooser.addDefault(new Noop());
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
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopExecute() {}

	@Override
	public void autonomousInitialize() {
		RobotMap.Component.navx.zeroYaw();
		autonomousCommand = new RunAllSequential(
			autoApproachChooser.getSelected(),
			autoRealignChooser.getSelected(),
			new GearPegWiggleAndWithdraw(),
			autoFinishChooser.getSelected());
		autonomousCommand.start();
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
