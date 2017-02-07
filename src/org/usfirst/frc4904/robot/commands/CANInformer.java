package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.standard.custom.CustomCAN;
import edu.wpi.first.wpilibj.command.Command;

public class CANInformer extends Command {
	/**
	 * Defines a functional interface for supplying message values for CAN.
	 *
	 */
	@FunctionalInterface
	public static interface CANMessageSupplier {
		byte[] getCANMessage();
	}
	protected final CANMessageSupplier messageSupplier;
	protected int numberOfIterationsRemaining;
	protected CustomCAN canSender;

	/**
	 * Create a CANInformer command for a given CAN id and message supplier for a certain number of iterations
	 * 
	 * @param id
	 *        the CAN id to send the messages to
	 * @param numberOfIterations
	 *        the number of messages to send in total
	 * @param messageSupplier
	 *        a supplier functionx
	 */
	public CANInformer(int id, int numberOfIterations, CANMessageSupplier messageSupplier) {
		setRunWhenDisabled(true);
		numberOfIterationsRemaining = numberOfIterations;
		this.messageSupplier = messageSupplier;
		canSender = new CustomCAN("CANInformer@" + id, id);
	}

	public CANInformer(int id, CANMessageSupplier messageSupplier) {
		this(id, -1, messageSupplier);
	}

	@Override
	protected void execute() {
		if (numberOfIterationsRemaining > -1) {
			--numberOfIterationsRemaining;
		}
		canSender.write(messageSupplier.getCANMessage());
	}

	@Override
	protected boolean isFinished() {
		return numberOfIterationsRemaining == 0;
	}
}