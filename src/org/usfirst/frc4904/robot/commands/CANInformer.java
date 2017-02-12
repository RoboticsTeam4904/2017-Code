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
	protected final CustomCAN canSender;
	private boolean runsDefinitely = true;

	/**
	 * Create a CANInformer command for a given CAN id
	 * and message supplier for a certain number of iterations.
	 * 
	 * @param id
	 *        the CAN id to send the messages to
	 * @param numberOfIterations
	 *        the number of messages to send in total
	 * @param messageSupplier
	 *        a supplier function
	 */
	public CANInformer(int id, int numberOfIterations, CANMessageSupplier messageSupplier) {
		setRunWhenDisabled(true);
		numberOfIterationsRemaining = numberOfIterations;
		this.messageSupplier = messageSupplier;
		canSender = new CustomCAN("CANInformer@" + id, id);
	}

	/**
	 * Create a CANInformer command for a given CAN id
	 * and message supplier that runs indefinitely.
	 * 
	 * @param id
	 *        the CAN id to send the messages to
	 * @param messageSupplier
	 *        a supplier function
	 */
	public CANInformer(int id, CANMessageSupplier messageSupplier) {
		this(id, 0, messageSupplier);
		runsDefinitely = false;
	}

	@Override
	protected void execute() {
		if (numberOfIterationsRemaining > 0) {
			--numberOfIterationsRemaining;
		}
		canSender.write(messageSupplier.getCANMessage());
	}

	@Override
	protected boolean isFinished() {
		return runsDefinitely && numberOfIterationsRemaining <= 0;
	}
}