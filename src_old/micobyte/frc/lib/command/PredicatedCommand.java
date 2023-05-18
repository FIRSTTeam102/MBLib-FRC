package micobyte.frc.lib.command;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import micobyte.frc.lib.MBLibUtil;

/**
 * A {@link Command} that executes either one {@link Command} or another, based upon a value {@link Supplier supplied}
 */
public class PredicatedCommand extends ConditionalCommand {
	/** The {@link Supplier} */
	protected final Supplier<Boolean> predicate;
	
	/**
	 * Creates the {@link Command}, with the suggested name
	 * @param onTrue The {@link Command} to be executed if the {@link Supplier} returns true
	 * @param onFalse The {@link Command} to be executed if the {@link Supplier} returns false
	 * @param predicate The {@link Supplier}
	 */
	public PredicatedCommand(Command onTrue, Command onFalse, Supplier<Boolean> predicate) { this(null, onTrue, onFalse, predicate); }
	
	/**
	 * Creates the {@link Command}
	 * @param name The name for the {@link Command}
	 * @param onTrue The {@link Command} to be executed if the {@link Supplier} returns true
	 * @param onFalse The {@link Command} to be executed if the {@link Supplier} returns false
	 * @param predicate The {@link Supplier}
	 */
	public PredicatedCommand(String name, Command onTrue, Command onFalse, Supplier<Boolean> predicate) { super((name == null ? getSuggestedName(onTrue, onFalse) : name), onTrue, onFalse); this.predicate = predicate; }
	
	protected boolean condition() { return predicate.get().booleanValue(); }
	
	/**
	 * Gets the suggested name for the given commands
	 * @param onTrue The {@link Command} to be executed if the {@link Supplier} returns true
	 * @param onFalse The {@link Command} to be executed if the {@link Supplier} returns false
	 * @return The suggested name
	 */
	public static String getSuggestedName(Command onTrue, Command onFalse) { return "Conditional: { onTrue: " + MBLibUtil.getName(onTrue) + ", onFalse: " + MBLibUtil.getName(onFalse) + " }"; }
}