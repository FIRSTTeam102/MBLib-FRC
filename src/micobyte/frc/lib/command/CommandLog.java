package micobyte.frc.lib.command;

import java.util.function.Supplier;

import micobyte.frc.lib.IStatusLogger;

/**
 * A {@link CommandPrint print command} that prints to a {@link IStatusLogger status logger}
 */
public class CommandLog extends CommandPrint {
	/**
	 * The {@link IStatusLogger logger} used for the logging<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	protected IStatusLogger logger;
	
	/**
	 * Creates the {@link edu.wpi.first.wpilibj.command.Command Command}
	 * @param logger The {@link IStatusLogger logger} to use
	 * @param message The message
	 * @param formatProvider The {@link Supplier} of the parameters to be {@link String#format(String, Object...) formatted} in
	 */
	public CommandLog(IStatusLogger logger, String message, Supplier<Object[]> formatProvider) { super(message, formatProvider); this.logger = logger; }
	
	protected void print(String str) { logger.logStatus(str); }
}