package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.LogKitten;
import edu.wpi.first.wpilibj.command.Command;

public class AlignAssist extends Command {
	private final GearAlign gearAlign;
	private final FineModifier fineModifier;
	private final EnableFineModifier enableFineModifier;

	public AlignAssist(GearAlign gearAlign, FineModifier fineModifier) {
		this.gearAlign = gearAlign;
		this.fineModifier = fineModifier;
		enableFineModifier = new EnableFineModifier(fineModifier);
	}

	@Override
	public void execute() {
		LogKitten.wtf("Gear Align: " + gearAlign.isAligned());
		LogKitten.wtf("Fine Mod:   " + enableFineModifier.isRunning());
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
