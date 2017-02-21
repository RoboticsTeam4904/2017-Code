package org.usfirst.frc4904.sovereignty.profiles;


public interface StoppableRunnable extends Runnable {
	public abstract void stoppableRun();

	@Override
	default void run() {
		setStopped(false);
		while (!isStopped()) {
			stoppableRun();
			stop();
		}
	}

	boolean isStopped();

	void setStopped(boolean isStop);

	default void stop() {
		setStopped(true);
	}
}
