package org.usfirst.frc4904.robot.commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import edu.wpi.first.wpilibj.command.Command;

public class CalibrateCameraExposureSockets extends Command {

	private final String HOSTNAME = "tegra-ubuntu.local";
	private final int PORT_NUMBER = 5001;
	private PrintWriter output;

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void execute() {
		Socket s = connect();
		try {
			output = new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output.println("do the autocalibrate thing please");
		output.flush();
	}

	private Socket connect() {
		while (true) {

			try {
				Socket s = new Socket(InetAddress.getByName(HOSTNAME), PORT_NUMBER);
				System.out.println("Socket achieved");
				return s;
			} catch (IOException e) {
				System.out.println("no connection");
				// fatalError("Error connecting to the server, try again
				// later.");
			}
		}
	}

	public static void main(String[] args) {
		new CalibrateCameraExposureSockets();
	}

	public CalibrateCameraExposureSockets() {
		execute();
	}

}
