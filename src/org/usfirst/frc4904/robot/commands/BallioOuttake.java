package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.standard.commands.RunIf;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallioOuttake extends CommandGroup implements OverridableCommand {
	public boolean ballioOuttakeHopperOverride;

	public BallioOuttake() {
		super("BallioOuttake");
		requires(RobotMap.Component.ballIO);
		addSequential(new BallioDoorSetOuttake());
		addSequential(new RunIf(new HopperSetBallio(), this::isNotOverridden));
		addParallel(new MotorConstant(RobotMap.Component.ballIO.directionalRoller, -1.0));
		addParallel(new MotorConstant(RobotMap.Component.ballIO.elevatorAndIntakeRoller, 1.0));
		addSequential(new MotorConstant(RobotMap.Component.ballIO.hopperRollers, 1.0));
		addSequential(new RunIf(new HopperSetBallio(), this::isNotOverridden));
		addParallel(new MotorConstant(RobotMap.Component.ballIO.directionalRoller, BallIO.DIRECTIONAL_ROLLER_OUTTAKE_SPEED));
		addParallel(new MotorConstant(RobotMap.Component.ballIO.elevatorAndIntakeRoller,
			BallIO.ELEVATOR_AND_INTAKE_ROLLER_FORWARD_SPEED));
		addSequential(new MotorConstant(RobotMap.Component.ballIO.hopperRollers, BallIO.HOPPER_ROLLERS_FORWARD_SPEED));
	}

	@Override
	public void setOverride(boolean ballioOuttakeOverride) {
		this.ballioOuttakeHopperOverride = ballioOuttakeOverride;
	}

	@Override
	public boolean isOverridden() {
		return ballioOuttakeHopperOverride;
	}
}
