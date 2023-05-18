package micobyte.frc.lib.command;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * A {@link edu.wpi.first.wpilibj.command.Command Command} to print a message, but better than a
 * {@link edu.wpi.first.wpilibj.command.PrintCommand PrintCommand}, because it can
 * {@link String#format(String, Object...) format} a {@link String} with parameters supplied by a
 * {@link Supplier}.
 */
public class CommandPrint extends InstantCommand {
	/** The message */
	private String message;
	/** The {@link Supplier} of the parameters to be {@link String#format(String, Object...) formatted} in */
	private Supplier<Object[]> formatProvider;
	
	/**
	 * Creates the {@link edu.wpi.first.wpilibj.command.Command Command}
	 * @param message The message
	 * @param formatProvider The {@link Supplier} of the parameters to be {@link String#format(String, Object...) formatted} in
	 */
	public CommandPrint(String message, Supplier<Object[]> formatProvider) { super("Print message: " + message); this.message = message; this.formatProvider = formatProvider; }
	
	public void initialize() { System.out.println(String.format(message, formatProvider.get())); }
}