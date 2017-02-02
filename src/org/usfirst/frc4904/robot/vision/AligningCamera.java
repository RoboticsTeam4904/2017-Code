package org.usfirst.frc4904.robot.vision;


import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AligningCamera implements PIDSource {
	public static final String TABLE_NAME = "GRIP/myContoursReport";
	public static final String FIELD_DEGREES = "degrees";
	public static final String FIELD_DISTANCE = "distance";
	public static final String FIELD_VISIBLE = "visible";
	protected NetworkTable cameraTable;
	private PIDSourceType sourceType;

	public AligningCamera(PIDSourceType sourceType, String cameraTableName) {
		this.sourceType = sourceType;
		cameraTable = NetworkTable.getTable(cameraTableName);
	}

	public AligningCamera(PIDSourceType sourceType) {
		this(sourceType, AligningCamera.TABLE_NAME);
	}

	public AligningCamera() {
		this(PIDSourceType.kDisplacement, AligningCamera.TABLE_NAME);
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

	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}

	@Override
	public double pidGet() {
		return getDegrees();
	}

	@Override
	public void setPIDSourceType(PIDSourceType sourceType) {
		this.sourceType = sourceType;
	}
}
