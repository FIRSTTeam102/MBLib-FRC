package micobyte.frc.lib;

import java.util.function.Supplier;

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
	
	/**
	 * Creates an {@link IUpdateable updateable} whose "should terminate" value is returned by the given {@link Supplier}
	 * @param update The {@link IUpdateable updateable} to wrap
	 * @param shouldTerminate The {@link Supplier} for the "should terminate" values
	 * @return The {@link IUpdateable updateable}
	 */
	public static IUpdateable withTermination(IUpdateable update, Supplier<Boolean> shouldTerminate) {
		return new IUpdateable() {
			public void update() { update.update(); }
			public boolean shouldTerminate() { return shouldTerminate.get() || update.shouldTerminate(); }
		};
	}
}