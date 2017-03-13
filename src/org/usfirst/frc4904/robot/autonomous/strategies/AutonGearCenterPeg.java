package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.autonomous.commands.GearCenterPegApproach;
import org.usfirst.frc4904.robot.autonomous.commands.GearPegWiggleAndWithdraw;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearCenterPeg extends CommandGroup {
	public static final double TIME_INITIAL_APPROACH = 1.5;

	public AutonGearCenterPeg() {
		addSequential(new GearCenterPegApproach());
		addSequential(new GearPegWiggleAndWithdraw());
	}
}