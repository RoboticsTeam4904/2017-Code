package org.usfirst.frc4904.sovereignty;


public interface FusibleSensor<T> {
	T getValue();

	boolean trustable();
}
