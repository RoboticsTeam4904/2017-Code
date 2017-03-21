package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.KittenCommand;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.RunUnless;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAlign extends CommandGroup {
	protected static final int MAX_ALIGNMENT_CYCLES = 3;
	protected static final double MAX_CYCLE_TIME_SECONDS = 1;
	protected static final double INTER_CYCLE_DELAY_TIME_SECONDS = 0.25;
	protected static final double MAXIMUM_CAMERA_DEGREE_TOLERANCE = 2.5;

	public GearAlign() {
		for (int i = 0; i < GearAlign.MAX_ALIGNMENT_CYCLES; i++) {
			// The pre-turn wait command
			Command wait = new WaitCommand(GearAlign.INTER_CYCLE_DELAY_TIME_SECONDS);
			// The actual turn command
			Command core = new VisionTurn(RobotMap.Component.gearAlignCamera);
			// Limit the maximum time of the turn command
			Command timeLimited = new RunFor(core, GearAlign.MAX_CYCLE_TIME_SECONDS);
			// Only run if we're not already aligned
			Command ifNotAligned = new RunUnless(timeLimited,
				() -> !Double.isNaN(RobotMap.Component.gearAlignCamera.pidGet())
					&& Math.abs(RobotMap.Component.gearAlignCamera.pidGet()) <= GearAlign.MAXIMUM_CAMERA_DEGREE_TOLERANCE);
			// Actually add all commands
			addSequential(wait);
			addSequential(ifNotAligned);
		}
		addSequential(new KittenCommand("Done gear aligning (I did my best)", LogKitten.LEVEL_VERBOSE));
	}
}
