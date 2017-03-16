package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.KittenCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAlign extends CommandGroup {
	public GearAlign() {
		addSequential(new WaitCommand(0.25));
		addSequential(new VisionTurn(RobotMap.Component.gearAlignCamera));
		addSequential(new KittenCommand("Done gear aligning (I did my best)", LogKitten.LEVEL_WTF));
	}
}
