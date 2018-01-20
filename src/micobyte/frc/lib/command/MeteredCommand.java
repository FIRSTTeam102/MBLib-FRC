package micobyte.frc.lib.command;

import edu.wpi.first.wpilibj.command.Command;

import micobyte.frc.lib.io.TextMeter;
import micobyte.frc.lib.io.TextMeter.IMeterable;

/**
 * A {@link Command} that is an {@link IMeterable}
 */
public abstract class MeteredCommand extends Command implements IMeterable {
	/** The {@link TextMeter meter} */
	private TextMeter meter;
	
	/**
	 * Creates the {@link MeteredCommand}, with a default name.
	 */
	public MeteredCommand() {}
	
	/**
	 * Creates the {@link MeteredCommand}, with the given name.
	 * @param name The name
	 */
	public MeteredCommand(String name) { super(name); }
	
	{ meter = createAndStart(); }
	
	public String getTitleForMeter() { return getName(); }
	
	public void end() { meter.startShutdown(); }
}