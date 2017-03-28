package org.usfirst.frc4904.robot.vision;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.usfirst.frc4904.standard.LogKitten;

public class CameraServer implements Runnable {
	public final static int CAMERA_PORT = 5802;
	protected ServerSocket socket;
	protected double cameraAngle;
	private final Object lock = new Object();

	public CameraServer() {
		try {
			socket = new ServerSocket(CameraServer.CAMERA_PORT);
		}
		catch (IOException ex) {
			LogKitten.ex(ex);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				LogKitten.wtf("Waiting for connection...");
				Socket client = socket.accept();
				if (client != null) {
					StringBuilder newData = new StringBuilder();
					int next = client.getInputStream().read();
					while (next != 10 && next > 0) {
						newData.append((char) next);
						next = client.getInputStream().read();
					}
					client.close();
					synchronized (lock) {
						cameraAngle = Double.parseDouble(newData.toString());
					}
					LogKitten.wtf(cameraAngle);
				}
			}
			catch (IOException e) {
				LogKitten.ex(e);
			}
		}
	}

	public double read() {
		synchronized (lock) {
			return cameraAngle;
		}
	}
}
