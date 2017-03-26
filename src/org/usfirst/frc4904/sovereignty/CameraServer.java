package org.usfirst.frc4904.sovereignty;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.usfirst.frc4904.standard.LogKitten;

public class CameraServer implements Runnable {
	protected ServerSocket socket;
	protected double lastAngle;

	public CameraServer() {
		try {
			socket = new ServerSocket(5802);
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
					lastAngle = Double.parseDouble(newData.toString());
					LogKitten.w(lastAngle);
				}
			}
			catch (IOException e) {
				LogKitten.ex(e);
			}
		}
	}

	public double read() {
		return lastAngle;
	}
}
