package org.usfirst.frc4904.robot.vision;


import java.util.Iterator;
import org.usfirst.frc4904.standard.util.RollingLinkedList;

public class FrameScoreSource {
	
	public static final int DEFAULT_HISTORY = 25;
	protected final RollingLinkedList<FrameValue> history;
	
	public FrameScoreSource(int historyLength) {
		history = new RollingLinkedList<>(historyLength);
	}
	
	public float getScore() {
		float scoreTrack = 0.0f;
		int frameIndex = 0;
		Iterator<FrameValue> iterator = history.iterator();
		while (iterator.hasNext()) {
			frameIndex += 1;
			FrameValue frame = iterator.next();
			scoreTrack += (1 / frameIndex) * frame.getValue() * (frame.getVisible() ? 1 : -1);
		}
		return scoreTrack;
	}
}
