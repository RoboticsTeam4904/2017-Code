package org.usfirst.frc4904.robot.vision;


import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AligningCamera implements PIDSource {
	
	public static final String TABLE_NAME = "GRIP/myContoursReport";
	public static final String FIELD_DEGREES = "degrees";
	public static final String FIELD_DISTANCE = "distance";
	public static final String FIELD_VISIBLE = "visible";
	// private double invisibleValue;
	private double visibleValue;
	private boolean hasBeenVisible = false;
	protected NetworkTable cameraTable;
	private PIDSourceType sourceType;
	
	public AligningCamera(PIDSourceType sourceType, String cameraTableName) {
		this.sourceType = sourceType;
		cameraTable = NetworkTable.getTable(cameraTableName);
	}
	
	public AligningCamera(PIDSourceType sourceType) {
		this(sourceType, AligningCamera.TABLE_NAME);
	}
	
	public float getDegrees() {
		return (float) cameraTable.getNumber(AligningCamera.FIELD_DEGREES, Float.NaN);
	}
	
	public float getDistance() {
		return (float) cameraTable.getNumber(AligningCamera.FIELD_DISTANCE, Float.NaN);
	}
	
	public boolean isVisible() {
		return cameraTable.getBoolean(AligningCamera.FIELD_VISIBLE, getDegrees() == Float.NaN || getDistance() == Float.NaN);
	}
	
	public boolean isCurrentlyVisible() {
		return cameraTable.getBoolean(AligningCamera.FIELD_VISIBLE, Boolean.FALSE);
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}
	
	// public double pidGetRaw() {
	// return getDegrees();
	// }
	@Override
	public double pidGet() {
		return getCorrectedPID(getDegrees(), isCurrentlyVisible());
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType sourceType) {
		this.sourceType = sourceType;
	}
	
	public double getCorrectedPID(double rawValue, boolean isVisible) {
		if (isVisible) {
			visibleValue = rawValue;
			if (!hasBeenVisible) {
				hasBeenVisible = true;
			}
			return visibleValue;
		} else {
			// invisibleValue = rawValue;
			if (!hasBeenVisible) {
				return Double.NaN;
			} else {
				return visibleValue;
			}
		}
	}
}
