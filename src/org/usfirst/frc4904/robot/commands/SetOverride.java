package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public class SetOverride extends Command {
	protected final boolean setValue;
	protected final Overridable[] overridables;

	public SetOverride(boolean setValue, Overridable... overridables) {
		this.setValue = setValue;
		this.overridables = overridables;
	}

	@Override
	protected void initialize() {
		for (Overridable overridable : overridables) {
			overridable.setOverridden(setValue);
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
