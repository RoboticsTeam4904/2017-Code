package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.standard.custom.CustomCAN;
import edu.wpi.first.wpilibj.command.Command;

public abstract class CANInformer extends Command {
	protected int numberOfIterationsRemaining;
	protected final CustomCAN canSender;
	protected boolean runsIndefinitely = false;

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
	public CANInformer(int id, int numberOfIterations) {
		setRunWhenDisabled(true);
		numberOfIterationsRemaining = numberOfIterations;
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
	public CANInformer(int id) {
		this(id, 0);
		runsIndefinitely = true;
	}

	/**
	 * Returns the message that will be sent over CAN.
	 * 
	 * @return the message as a list of exactly 8 bytes
	 */
	protected abstract byte[] getCANMessage();

	@Override
	protected final void execute() {
		if (numberOfIterationsRemaining > 0) {
			--numberOfIterationsRemaining;
		}
		canSender.write(getCANMessage());
	}

	@Override
	protected final boolean isFinished() {
		return !runsIndefinitely && numberOfIterationsRemaining <= 0;
	}
}