package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class DumpUp extends Command {
	
	public DumpUp() {
		requires(RobotMap.Component.ballDumper);
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		if (RobotMap.Component.elevatorEncoder.get() != RobotMap.Constant.RobotMetric.elevatorConstant) {
			RobotMap.Component.ballDumper.spinElevator(1.0);
		} else {
			RobotMap.Component.ballDumper.stopSpinElevator();
			RobotMap.Component.ballDumper.beingSpinOuttake(1.0);
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
	}
}
