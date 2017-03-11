package org.usfirst.frc4904.robot.commands;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import org.usfirst.frc4904.standard.LogKitten;
import edu.wpi.first.wpilibj.command.Command;

public class CalibrateCameraExposureSockets extends Command {
	protected static final String HOSTNAME = "10.49.4.73";
	protected static final int PORT_NUMBER = 5800;
	protected static final String AUTOCALIBRATION_MESSAGE = "do the autocalibrate thing please";
	protected PrintWriter output;
	protected Socket socket;

	@Override
	protected void initialize() {
		socket = null;
		output = null;
	}

	@Override
	protected void execute() {
		if (socket == null) {
			try {
				socket = new Socket(CalibrateCameraExposureSockets.HOSTNAME, CalibrateCameraExposureSockets.PORT_NUMBER);
			}
			catch (IOException e) {
				LogKitten.w("Autocalibration could not connect to the socket");
				LogKitten.ex(e);
				return;
			}
		}
		if (output == null) {
			try {
				output = new PrintWriter(socket.getOutputStream());
			}
			catch (IOException e) {
				LogKitten.ex(e);
				return;
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return socket != null && output != null;
	}

	@Override
	protected void end() {
		LogKitten.v("Autocalibration socket sucessfully connected");
		output.println(CalibrateCameraExposureSockets.AUTOCALIBRATION_MESSAGE);
		output.flush();
		output.close();
		try {
			socket.close();
		}
		catch (IOException e) {
			LogKitten.ex(e);
		}
	}
}
