package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurnAbsolute;
import org.usfirst.frc4904.standard.humaninput.Driver;
import edu.wpi.first.wpilibj.command.Command;

public class DefaultDriver extends Driver {
	public static final double CONTROLLER_MIN_THRESH = 0.1;
	
	public DefaultDriver() {
		super("DefaultDriver");
	}
	
	@Override
	public void bindCommands() {
		Command normalDrive = new ChassisMove(RobotMap.Component.chassis, this);
		RobotMap.HumanInput.Driver.xbox.dPad.up.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, 180, RobotMap.Component.navx, RobotMap.Component.chassisMC));
		RobotMap.HumanInput.Driver.xbox.dPad.up.whenReleased(normalDrive);
		RobotMap.HumanInput.Driver.xbox.dPad.down.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, 0, RobotMap.Component.navx, RobotMap.Component.chassisMC));
		RobotMap.HumanInput.Driver.xbox.dPad.down.whenReleased(normalDrive);
		RobotMap.HumanInput.Driver.xbox.dPad.left.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, 270, RobotMap.Component.navx, RobotMap.Component.chassisMC));
		RobotMap.HumanInput.Driver.xbox.dPad.left.whenReleased(normalDrive);
		RobotMap.HumanInput.Driver.xbox.dPad.right.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, 90, RobotMap.Component.navx, RobotMap.Component.chassisMC));
		RobotMap.HumanInput.Driver.xbox.dPad.right.whenReleased(normalDrive);
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		return 0;
	}
	
	@Override
	public double getTurnSpeed() {
		return 0;
	}
}