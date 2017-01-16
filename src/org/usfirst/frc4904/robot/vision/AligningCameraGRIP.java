package org.usfirst.frc4904.robot.vision;


import edu.wpi.first.wpilibj.PIDSourceType;

public class AligningCameraGRIP extends AligningCamera {
	public static final String TABLE_NAME = "GRIP/myContoursReport";
	public static final String FIELD_DEGREES = "centerX";
	public static final String FIELD_DISTANCE = "centerY";
	public static final String FIELD_VISIBLE = "visible";
	
	public AligningCameraGRIP(PIDSourceType sourceType) {
		super(sourceType, AligningCameraGRIP.TABLE_NAME);
	}
	
	@Override
	public float getDegrees() {
		double[] data = cameraTable.getNumberArray(AligningCamera.FIELD_DEGREES, new double[] {});
		System.out.println("Degree Data: " + data);
		if (data.length == 2) {
			return (float) (data[0] + data[1]) / 2;
		} else if (data.length == 1) {
			return (float) data[0];
		} else {
			return 0.0f;
		}
	}
	
	@Override
	public float getDistance() {
		double[] data = cameraTable.getNumberArray(AligningCamera.FIELD_DISTANCE, new double[] {});
		System.out.println("Distance Data: " + data);
		if (data.length == 2) {
			return (float) (data[0] + data[1]) / 2;
		} else if (data.length == 1) {
			return (float) data[0];
		} else {
			return 0.0f;
		}
	}
}
