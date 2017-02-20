package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.commands.motor.speedmodifiers.SetEnableableModifier;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.EnableableModifier;
import edu.wpi.first.wpilibj.command.Command;

public class AlignAssist extends Command {
	protected final GearAlign gearAlign;
	protected final EnableableModifier fineModifier;
	protected final SetEnableableModifier enableFineModifier;
	protected final SetEnableableModifier disableFineModifier;

	public AlignAssist(GearAlign gearAlign, EnableableModifier fineModifier) {
		this.gearAlign = gearAlign;
		this.fineModifier = fineModifier;
		enableFineModifier = new SetEnableableModifier(true, fineModifier);
		disableFineModifier = new SetEnableableModifier(false, fineModifier);
	}

	@Override
	public void execute() {
		if (gearAlign.isAligned() && !fineModifier.isEnabled()) {
			enableFineModifier.start();
		} else if (!gearAlign.isAligned() && fineModifier.isEnabled()) {
			disableFineModifier.start();
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
