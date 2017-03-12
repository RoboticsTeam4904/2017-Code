package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetOverride extends InstantCommand {
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
}
