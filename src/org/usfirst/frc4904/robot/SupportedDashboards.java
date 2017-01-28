package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.sovereignty.PIDContainer.PIDValueModifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public enum SupportedDashboards {
	SMARTDASHBOARD(new PIDValueModifier() {
		@Override
		public void pushValue(String key, double value) {
			SmartDashboard.putNumber(key, value);
		}

		@Override
		public double pullValue(String key) {
			return SmartDashboard.getNumber(key, 0.0);
		}
	});
	private final PIDValueModifier modifier;

	private SupportedDashboards(PIDValueModifier modifier) {
		this.modifier = modifier;
	}

	public PIDValueModifier getModifier() {
		return modifier;
	}
}