package micobyte.frc.lib;

import micobyte.frc.lib.command.CommandUpdate;

/**
 * A {@link FunctionalInterface functional interface} that provides the facility of creating something that may be updated,
 * usually by a {@link CommandUpdate}.
 */
public interface IUpdateable {
	
	/**
	 * A method that is called to update the {@link IUpdateable} each tick.
	 */
	public void update();
	
	/**
	 * @return Whether the {@link IUpdateable} should stop being updated
	 */
	public default boolean shouldTerminate() { return false; }
}