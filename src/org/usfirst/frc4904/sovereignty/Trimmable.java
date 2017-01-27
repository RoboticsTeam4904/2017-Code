package org.usfirst.frc4904.sovereignty;


public interface Trimmable {
	void setTrimIncrement(double increment);
	
	double getTrimIncrement();
	
	void setTrim(double trim);
	
	double getTrim();
	
	void adjustTrim(boolean positive);
	
	void adjustTrim(double change);
}
