package micobyte.frc.lib.command;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import micobyte.frc.lib.hid.RumbleSeries;

/**
 * A {@link CommandGroup} with several additions for adding commonly used {@link Command commands}
 */
public class SimpleCommandGroup extends CommandGroup {
	
	/**
	 * Creates an unnamed {@link SimpleCommandGroup}
	 */
	public SimpleCommandGroup() {}
	
	/**
	 * Creates a {@link SimpleCommandGroup} with the given name
	 * @param name The name
	 */
	public SimpleCommandGroup(String name) { super(name); }
	
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
	 * @param messageProvider
	 */
	public void addPrint(Supplier<String> messageProvider) { addPrint("%s", () -> new Object[] { messageProvider.get() }); }
	
	/**
	 * Adds a {@link CommandPrint} sequentially
	 * @param message The message
	 * @param formatProvider The {@link Supplier} of the parameters to be {@link String#format(String, Object...) formatted} in
	 */
	public void addPrint(String message, Supplier<Object[]> formatProvider) { addSequential(new CommandPrint(message, formatProvider)); }
	
	/**
	 * Adds a {@link WaitCommand wait} in for the given ammount of time
	 * @param time The ammount of time to wait, in seconds
	 */
	public void addWait(double time) { addSequential(new WaitCommand(time)); }
}