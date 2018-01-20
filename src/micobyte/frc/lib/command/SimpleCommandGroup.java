package micobyte.frc.lib.command;

import java.util.function.Supplier;
import java.util.Vector;

import edu.wpi.first.wpilibj.GenericHID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import micobyte.frc.lib.IStatusLogger;
import micobyte.frc.lib.io.RumbleSeries;

/**
 * A {@link CommandGroup} with several additions for adding commonly used {@link Command commands}
 */
public class SimpleCommandGroup extends CommandGroup {
	/**
	 * The {@link IStatusLogger logger} used by default for every {@link SimpleCommandGroup} unless another is defined for it<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	protected static IStatusLogger defaultLogger;
	
	/**
	 * The {@link IStatusLogger logger} used for the logging<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	protected IStatusLogger logger;
	
	/**
	 * Creates an unnamed {@link SimpleCommandGroup}
	 */
	public SimpleCommandGroup() {}
	
	/**
	 * Creates a {@link SimpleCommandGroup} with the given name
	 * @param name The name
	 */
	public SimpleCommandGroup(String name) { super(name); }
	
	{
		setLogger(defaultLogger);
	}
	
	public void start() {
		try {
			int size = ((Vector<?>)getClass().getField("m_commands").get(this)).size();
			if(size < 1) addLog(() -> "doing nothing in command group \"" + getName() + "\"... yet.");
		} catch(Exception e) {}
		
		super.start();
	}
	
	/**
	 * Sets the default internal {@link IStatusLogger logger} for all {@link SimpleCommandGroup}s
	 * @param defaultLogger The new default {@link IStatusLogger logger}
	 */
	public static void setDefaultLogger(IStatusLogger defaultLogger) { SimpleCommandGroup.defaultLogger = defaultLogger; }
	
	/**
	 * Sets the internal {@link IStatusLogger logger} of the {@link SimpleCommandGroup command group}
	 * @param logger The new {@link IStatusLogger logger}
	 */
	public void setLogger(IStatusLogger logger) { this.logger = logger; }
	
	/**
	 * Adds a {@link PredicatedCommand} sequentially
	 * @param onTrue The {@link Command} to be executed if the {@link Supplier} returns true
	 * @param onFalse The {@link Command} to be executed if the {@link Supplier} returns false
	 * @param predicate The {@link Supplier}
	 */
	public void addConditional(Command onTrue, Command onFalse, Supplier<Boolean> predicate) { addSequential(new PredicatedCommand(null, onTrue, onFalse, predicate)); }
	
	/**
	 * Adds a {@link PredicatedCommand} sequentially, with the {@code onFalse} {@link Command} set to {@code null}
	 * @param onTrue The {@link Command} to be executed if the {@link Supplier} returns true
	 * @param predicate The {@link Supplier}
	 */
	public void addConditional(Command onTrue, Supplier<Boolean> predicate) { addConditional(onTrue, null, predicate); }
	
	/**
	 * Adds a {@link CommandPlayRumble} sequentially, <b>not</b> waiting for it to complete
	 * @param joystick The {@link GenericHID joystick} to rumble
	 * @param rumble The {@link RumbleSeries rumble} to play
	 */
	public void addPlayRumble(GenericHID joystick, RumbleSeries rumble) { addPlayRumble(joystick, rumble, false); }
	
	/**
	 * Adds a {@link CommandPlayRumble} sequentially
	 * @param joystick The {@link GenericHID joystick} to rumble
	 * @param rumble The {@link RumbleSeries rumble} to play
	 * @param wait Whether or not to wait for the {@link RumbleSeries rumble} to finish playing
	 */
	public void addPlayRumble(GenericHID joystick, RumbleSeries rumble, boolean wait) { addSequential(new CommandPlayRumble(joystick, rumble, wait)); }
	
	/**
	 * Adds a {@link CommandPrint} sequentially, just printing the message {@link Supplier supplied}
	 * @param messageProvider The {@link Supplier} of the message
	 */
	public void addPrint(Supplier<String> messageProvider) { addPrint("%s", () -> new Object[] { messageProvider.get() }); }
	
	/**
	 * Adds a {@link CommandPrint} sequentially
	 * @param message The message
	 * @param formatProvider The {@link Supplier} of the parameters to be {@link String#format(String, Object...) formatted} in
	 */
	public void addPrint(String message, Supplier<Object[]> formatProvider) { addSequential(new CommandPrint(message, formatProvider)); }
	
	/**
	 * Adds a {@link CommandLog} sequentially, just logging the message {@link Supplier supplied}, using the internally-supplied {@link IStatusLogger logger}<br>
	 * If no logger is present, it will throw a null-pointer exception on the calling of this method.
	 * @param messageProvider The {@link Supplier} of the message
	 */
	public void addLog(Supplier<String> messageProvider) { addLog("%s", () -> new Object[] { messageProvider.get() }); }
	
	/**
	 * Adds a {@link CommandLog} sequentially, using the internally-specified {@link IStatusLogger logger}<br>
	 * If no logger is present, it will throw a null-pointer exception on the calling of this method.
	 * @param message The message
	 * @param formatProvider The {@link Supplier} of the parameters to be {@link String#format(String, Object...) formatted} in
	 */
	public void addLog(String message, Supplier<Object[]> formatProvider) {
		if(logger == null) throw new NullPointerException("Logger is null, was it not yet set?");
		addSequential(new CommandLog(logger, message, formatProvider));
	}
	
	/**
	 * Adds a {@link WaitCommand wait} in for the given ammount of time
	 * @param time The ammount of time to wait, in seconds
	 */
	public void addWait(double time) { addSequential(new WaitCommand(time)); }
	
	/**
	 * Adds a {@link CommandSetDashboardLED set DB LED command} sequentially.
	 * @param led Which LED to set
	 * @param supp {@link Supplier} that gives what to set the LED to
	 */
	public void addSetDBLED(int led, Supplier<Boolean> supp) { addSequential(new CommandSetDashboardLED(led, supp)); }
	
	/**
	 * Adds a {@link CommandSetDashboardLED set DB LED command} sequentially, for the first LED.
	 * @param supp {@link Supplier} that gives what to set the LED to
	 */
	public void addSetFirstDBLED(Supplier<Boolean> supp) { addSetDBLED(0, supp); }
	
	/**
	 * Adds a {@link CommandSetDashboardLED set DB LED command} sequentially, for the second LED.
	 * @param supp {@link Supplier} that gives what to set the LED to
	 */
	public void addSetSecondDBLED(Supplier<Boolean> supp) { addSetDBLED(1, supp); }
	
	/**
	 * Adds a {@link CommandSetDashboardLED set DB LED command} sequentially, for the third LED.
	 * @param supp {@link Supplier} that gives what to set the LED to
	 */
	public void addSetThirdDBLED(Supplier<Boolean> supp) { addSetDBLED(2, supp); }
	
	/**
	 * Adds a {@link CommandSetDashboardLED set DB LED command} sequentially, for the fourth LED.
	 * @param supp {@link Supplier} that gives what to set the LED to
	 */
	public void addSetFourthDBLED(Supplier<Boolean> supp) { addSetDBLED(3, supp); }
	
	/**
	 * Adds a {@link CommandSetDashboardLED set DB LED command} sequentially, for the first LED.
	 * @param val What to set the LED to
	 */
	public void addSetFirstDBLED(boolean val) { addSetFirstDBLED(() -> val); }
	
	/**
	 * Adds a {@link CommandSetDashboardLED set DB LED command} sequentially, for the second LED.
	 * @param val What to set the LED to
	 */
	public void addSetSecondDBLED(boolean val) { addSetSecondDBLED(() -> val); }
	
	/**
	 * Adds a {@link CommandSetDashboardLED set DB LED command} sequentially, for the third LED.
	 * @param val What to set the LED to
	 */
	public void addSetThirdDBLED(boolean val) { addSetThirdDBLED(() -> val); }
	
	/**
	 * Adds a {@link CommandSetDashboardLED set DB LED command} sequentially, for the fourth LED.
	 * @param val What to set the LED to
	 */
	public void addSetFourthDBLED(boolean val) { addSetFourthDBLED(() -> val); }
}