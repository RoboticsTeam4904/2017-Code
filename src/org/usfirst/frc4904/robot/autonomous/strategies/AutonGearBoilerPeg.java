package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.autonomous.commands.GearBoilerPegApproach;
import org.usfirst.frc4904.robot.autonomous.commands.GearPegWiggleAndWithdraw;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearBoilerPeg extends CommandGroup {
	public AutonGearBoilerPeg(boolean useSensors) {
		addSequential(new GearBoilerPegApproach(useSensors));
		addSequential(new GearPegWiggleAndWithdraw());
	}
}
