package org.usfirst.frc4904.robot.vision;


import org.usfirst.frc4904.sovereignty.Fusible;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AligningCamera implements PIDSource, Fusible<Double> {
	public static final String TABLE_NAME = "Vision";
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

	public double getDegrees() {
		return cameraTable.getNumber(AligningCamera.FIELD_DEGREES, Double.NaN);
	}

	public double getDistance() {
		return cameraTable.getNumber(AligningCamera.FIELD_DISTANCE, Double.NaN);
	}

	public boolean isVisible() {
		return cameraTable.getBoolean(AligningCamera.FIELD_VISIBLE, Double.isNaN(getDegrees()) || Double.isNaN(getDistance()));
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

	@Override
	public Double getValue() {
		return getDegrees();
	}

	@Override
	public boolean trustable() {
		return isVisible();
	}

	@Override
	public String getName() {
		return "AligningCamera";
	}
}
