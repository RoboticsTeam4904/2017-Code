package org.usfirst.frc4904.sovereignty.profiles;


import java.util.LinkedList;
import java.util.Map;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.sovereignty.profiles.WheelTrajectory.Wheel;

public class MotionTrajectory {
	protected final SplineGenerator splineGenerator;
	protected final double plantWidth;
	protected final double tickTime, tickTotal;
	protected final WheelTrajectory leftWheel, rightWheel;
	protected final LinkedList<SplineSegment> featureSegments;
	/**
	 * Maps a tick to a left/right-specific segment, and the time that the tick occurs at relative to
	 * the beginning time of the segment.
	 */
	protected final Map<Integer, Tuple<Double, WheelTrajectorySegment>> leftWheelTickMap, rightWheelTickMap;

	/**
	 * 
	 * @param splineGenerator
	 * @param plantWidth
	 *        the width of whatever system we will be moving (in our case the robot)
	 * @param tickTime
	 *        the time that passes during a tick (in milliseconds)
	 * @param tickTotal
	 *        the total number of ticks that occur during our trajectory
	 */
	public MotionTrajectory(SplineGenerator splineGenerator, double plantWidth, double tickTime, double tickTotal) {
		this.splineGenerator = splineGenerator;
		this.plantWidth = plantWidth;
		this.tickTime = tickTime;
		this.tickTotal = tickTotal;
		// TODO: Update the threshold to reflect a real value.
		featureSegments = splineGenerator.generateFeatureSegments(1000, 0.0);
		leftWheel = new WheelTrajectory(this, featureSegments, Wheel.LEFT, tickTotal, tickTime);
		rightWheel = new WheelTrajectory(this, featureSegments, Wheel.RIGHT, tickTotal, tickTime);
		leftWheelTickMap = leftWheel.generateTickMap();
		rightWheelTickMap = rightWheel.generateTickMap();
	}

	/**
	 * Calculate the right/left motion trajectory points at a given tick. For ease of calculations,
	 * a map from ticks to the trajectory segment and time at which the tick occurs is pre-generated.
	 * 
	 * It is noted that there is a possibility for a segment with a duration smaller than the time of
	 * a tick could exist. However, we deem such a segment to be insignificant and the positional error
	 * that could occur due to this disregard is assumed to be handled by positional PID.
	 * 
	 * @param tick
	 * @return
	 */
	public Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> calcPoint(int tick) {
		Tuple<Double, WheelTrajectorySegment> leftWheelTick = leftWheelTickMap.get(tick);
		Tuple<Double, WheelTrajectorySegment> rightWheelTick = rightWheelTickMap.get(tick);
		return new Tuple<>(leftWheelTick.getY().findSetPoint(leftWheelTick.getX(), tick),
			rightWheelTick.getY().findSetPoint(rightWheelTick.getX(), tick));
	}

	public double calcMaxSpeed(double s) {
		return RobotMap.maxVel / (1 + plantWidth * splineGenerator.calcCurvature(s) / 2);
	}

	public double calcMaxAngularVel(double s) {
		return calcTurning(s, calcMaxSpeed(s)) * plantWidth; // = theta/second * circumference/2pi = distance / second
	}

	protected double calcTurning(double s, double speed) {
		return splineGenerator.calcCurvature(s) * speed;
	}
}