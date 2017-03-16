package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.KittenCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAlign extends CommandGroup {
	public GearAlign() {
		addSequential(new VisionTurn(RobotMap.Component.gearAlignCamera));
		addSequential(new KittenCommand("Done gear aligning (I did my best)", LogKitten.LEVEL_WTF));
	}
}
