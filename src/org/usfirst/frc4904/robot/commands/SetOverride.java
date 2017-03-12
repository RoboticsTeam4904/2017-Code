package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetOverride extends InstantCommand {
	protected final boolean isOverridden;
	protected final Overridable[] overridables;

	public SetOverride(boolean isOverridden, Overridable... overridables) {
		this.isOverridden = isOverridden;
		this.overridables = overridables;
	}

	@Override
	protected void initialize() {
		for (Overridable overridable : overridables) {
			overridable.setOverridden(isOverridden);
		}
	}
}
