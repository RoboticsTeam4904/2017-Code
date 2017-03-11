package org.usfirst.frc4904.robot.commands;


/**
 * This interface allows commands to easily override other commands
 */
public interface Overridable {
	/**
	 * Set whether this command is overridden.
	 * 
	 * @param isOverridden
	 *        Whether to override the command or not
	 */
	public void setOverridden(boolean isOverridden);

	/**
	 * Get whether this command is overridden.
	 * 
	 * @returns Whether this command is overridden.
	 */
	public boolean isOverridden();

	/**
	 * Get whether this command is NOT overridden.
	 * Useful for creating BooleanSuppliers with Java 8 syntax (e.g. this::isNotOverridden).
	 * 
	 * @returns Whether this command is NOT overridden.
	 */
	default boolean isNotOverridden() {
		return !isOverridden();
	}
}