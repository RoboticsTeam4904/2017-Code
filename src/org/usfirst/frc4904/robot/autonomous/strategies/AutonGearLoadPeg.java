package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.autonomous.commands.GearLoadPegApproach;
import org.usfirst.frc4904.robot.autonomous.commands.GearPegWiggleAndWithdraw;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearLoadPeg extends CommandGroup {
	public AutonGearLoadPeg(boolean useSensors) {
		addSequential(new GearLoadPegApproach(useSensors));
		addSequential(new GearPegWiggleAndWithdraw());
	}
}
