package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.RunIf;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallioCycle extends CommandGroup implements OverridableCommand {
	public BallioCycle() {
		super("BallioCycle");
		requires(RobotMap.Component.ballIO);
		addSequential(new BallioDoorSetOuttake());
		addSequential(new RunIf(new HopperSetBallio(), this::getNotOverride));
		addParallel(new MotorConstant(RobotMap.Component.ballIO.directionalRoller, 1.0));
		addParallel(new MotorConstant(RobotMap.Component.ballIO.elevatorAndIntakeRoller, 1.0));
		addSequential(new MotorConstant(RobotMap.Component.ballIO.hopperRollers, 1.0));
	}

	@Override
	public void setOverride(boolean setValue) {
		BallioOuttake.ballioCycleOverride = setValue;
	}

	@Override
	public boolean getOverride() {
		return BallioOuttake.ballioCycleOverride;
	}

	public boolean getNotOverride() {
		return !BallioOuttake.ballioCycleOverride;
	}
}
