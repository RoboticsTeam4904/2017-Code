package org.usfirst.frc4904.robot.vision;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import org.usfirst.frc4904.standard.LogKitten;

public class CameraServer implements Runnable {
	public final static int CAMERA_PORT = 5802;
	public final static int MAX_CAMERA_PACKET_SIZE = 64;
	protected DatagramSocket socket;
	protected DatagramPacket packet;
	protected double cameraAngle;
	private final Object lock = new Object();

	public CameraServer() {
		try {
			socket = new DatagramSocket(CameraServer.CAMERA_PORT);
			packet = new DatagramPacket(new byte[CameraServer.MAX_CAMERA_PACKET_SIZE], CameraServer.MAX_CAMERA_PACKET_SIZE);
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
				socket.receive(packet);
				if (packet != null) {
					synchronized (lock) {
						cameraAngle = Double.parseDouble(new String(packet.getData()));
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
