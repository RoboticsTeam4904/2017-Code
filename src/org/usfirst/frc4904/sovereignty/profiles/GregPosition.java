package org.usfirst.frc4904.sovereignty.profiles;


import java.util.HashMap;
import java.util.Map;

/**
 * GREGPosition represents the starting position of the robot:
 * - Gondola
 * - Relocation
 * - Enforcement
 * - Gedankenexperiments
 */
public enum GregPosition {
	POS1(Goal.gearGoal), POS2(Goal.gearGoal), POS3(Goal.gearGoal);
	private Map<String, Goal> goals;

	GregPosition(Goal... goals) {
		this.goals = new HashMap<String, Goal>();
		for (Goal goal : goals) {
			this.goals.put(goal.name, goal);
		}
	}
}
