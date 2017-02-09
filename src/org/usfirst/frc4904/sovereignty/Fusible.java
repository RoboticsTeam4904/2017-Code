package org.usfirst.frc4904.sovereignty;

import org.usfirst.frc4904.standard.custom.Named;

public interface Fusible<T> extends Named {
	T getValue();

	boolean trustable();
}
