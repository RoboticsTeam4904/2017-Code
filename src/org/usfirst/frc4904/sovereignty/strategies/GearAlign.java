package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.KittenCommand;
import org.usfirst.frc4904.standard.commands.RunFor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAlign extends CommandGroup {
	protected static final int ALIGNMENT_CYCLES = 3;
	protected static final double MAX_CYCLE_TIME_SECONDS = 1;
	protected static final double INTER_CYCLE_DELAY_TIME_SECONDS = 0.25;

	public GearAlign() {
		for (int i = 0; i < GearAlign.ALIGNMENT_CYCLES; i++) {
			// The pre-turn wait command
			Command wait = new WaitCommand(GearAlign.INTER_CYCLE_DELAY_TIME_SECONDS);
			// The actual turn command
			Command core = new VisionTurn(RobotMap.Component.gearAlignCamera);
			// Limit the maximum time of the turn command
			Command timeLimited = new RunFor(core, GearAlign.MAX_CYCLE_TIME_SECONDS);
			// Actually add all commands
			addSequential(wait);
			addSequential(timeLimited);
		}
		addSequential(new KittenCommand("Done gear aligning (I did my best)", LogKitten.LEVEL_VERBOSE));
	}
}
