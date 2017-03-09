package org.usfirst.frc4904.robot.commands;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import org.usfirst.frc4904.standard.LogKitten;
import edu.wpi.first.wpilibj.command.Command;

public class CalibrateCameraExposureSockets extends Command {
	protected static final String HOSTNAME = "tegra-ubuntu.local";
	protected static final int PORT_NUMBER = 5001;
	protected PrintWriter output;

	@Override
	protected void execute() {
		Socket s = connect();
		try {
			output = new PrintWriter(s.getOutputStream());
		}
		catch (IOException e) {
			LogKitten.ex(e);
		}
		output.println("do the autocalibrate thing please");
		output.flush();
	}

	private Socket connect() {
		while (true) {
			try {
				Socket s = new Socket(InetAddress.getByName(CalibrateCameraExposureSockets.HOSTNAME),
					CalibrateCameraExposureSockets.PORT_NUMBER);
				LogKitten.v("Socket achieved");
				return s;
			}
			catch (IOException e) {
				LogKitten.w("no connection");
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
