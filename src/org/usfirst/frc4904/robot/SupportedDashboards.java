package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.sovereignty.PIDContainer.PIDContainerModifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public enum SupportedDashboards {
	SMARTDASHBOARD(new PIDContainerModifier() {
		@Override
		public void pushValue(String key, double value) {
			SmartDashboard.putNumber(key, value);
		}

		@Override
		public double pullValue(String key) {
			return SmartDashboard.getNumber(key, 0.0);
		}
	});
	private final PIDContainerModifier modifier;

	private SupportedDashboards(PIDContainerModifier modifier) {
		this.modifier = modifier;
	}

	public PIDContainerModifier getModifier() {
		return modifier;
	}
}