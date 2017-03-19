package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.KittenCommand;
import org.usfirst.frc4904.standard.commands.RunFor;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAlign extends CommandGroup {
	protected static final int ALIGNMENT_CYCLES = 3;
	protected static final int MAX_CYCLE_TIME_SECONDS = 1;

	public GearAlign() {
		for (int i = 0; i < GearAlign.ALIGNMENT_CYCLES; i++) {
			addSequential(new WaitCommand(0.25));
			addSequential(new RunFor(new VisionTurn(RobotMap.Component.gearAlignCamera), GearAlign.MAX_CYCLE_TIME_SECONDS));
		}
		addSequential(new KittenCommand("Done gear aligning (I did my best)", LogKitten.LEVEL_WTF));
	}
}
