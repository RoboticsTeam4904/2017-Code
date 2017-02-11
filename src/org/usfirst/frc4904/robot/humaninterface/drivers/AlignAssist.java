package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import edu.wpi.first.wpilibj.command.Command;

public class AlignAssist extends Command {
	private final GearAlign gearAlign;
	private final EnableFineModifier enableFineModifier;

	public AlignAssist(GearAlign gearAlign, FineModifier fineModifier) {
		this.gearAlign = gearAlign;
		enableFineModifier = new EnableFineModifier(fineModifier);
	}

	@Override
	public void execute() {
		if (gearAlign.isAligned() && !enableFineModifier.isRunning()) {
			enableFineModifier.start();
		} else if (!gearAlign.isAligned() && enableFineModifier.isRunning()) {
			enableFineModifier.cancel();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	public void interrupted() {
		enableFineModifier.cancel();
	}
}
