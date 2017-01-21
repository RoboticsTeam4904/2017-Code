package org.usfirst.frc4904.robot.subsystems;


import java.nio.ByteBuffer;
import org.usfirst.frc4904.standard.custom.CustomCAN;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class CANLidar extends CustomCAN {
	
	/**
	 *
	 * @param name
	 *        Name of CAN sensor (not really needed)
	 * @param id
	 *        ID of CAN sensor (0x600 to 0x700, must correspond to a Teensy or similar)
	 * @param modes
	 *        Number of modes for the CAN sensor
	 */
	public CANLidar(String name, int id) {
		super(name, id);
	}
	
	/**
	 * Read an int from a CAN sensor
	 *
	 * @param mode
	 *        Which mode to read the sensor in (interpreted by the Teensy)
	 * @return
	 * 		The integer the Teensy returned for that mode
	 *
	 * @throws InvalidSensorException
	 *         If the available data is more than one second old,
	 *         this function will throw an InvalidSensorException
	 *         to indicate that.
	 * 
	 */
	public int[] readlidar() throws InvalidSensorException {
		write(new byte[] {0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01});// write to trigger read
		ByteBuffer rawData = readBuffer();
		if (rawData != null && rawData.remaining() > 7) {
			rawData.rewind();
			long data = Long.reverseBytes(rawData.getLong());
			int xCoordinate = (int) (data & 0xFFFFFFFF);
			int yCoordinate = (int) (data >> 32);
			return new int[] {xCoordinate, yCoordinate};
		}
		return null;
	}
}
