package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.commands.Overridable;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class OverridableSubsystem extends Subsystem implements Overridable {
	private boolean isOverridden = false;

	@Override
	public void setOverridden(boolean isOverridden) {
		this.isOverridden = isOverridden;
	}

	@Override
	public boolean isOverridden() {
		return isOverridden;
	}
}
