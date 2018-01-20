package micobyte.frc.lib.command;

import edu.wpi.first.wpilibj.command.TimedCommand;

import micobyte.frc.lib.io.TextMeter;
import micobyte.frc.lib.io.TextMeter.IMeterable;

public class MeteredTimedCommand extends TimedCommand implements IMeterable {
	/** The {@link TextMeter meter} */
	private TextMeter meter;
	
	/** Local copy of the timeout, because it isn't accessible otherwise */
	private double timeout;
	
	/**
	 * Creates the {@link MeteredTimedCommand}, with the given name.
	 * @param name The name
	 * @param timeout The time (in seconds) before this command "times out"
	 */
	public MeteredTimedCommand(String name, double timeout) { super(name, timeout); this.timeout = timeout; }
	
	/**
	 * Creates the {@link MeteredTimedCommand}, with a default name.
	 * @param timeout The time (in seconds) before this command "times out"
	 */
	public MeteredTimedCommand(double timeout) { super(timeout); this.timeout = timeout; }
	
	{ meter = createAndStart(); }

	public float getProgress() { return (float)(timeSinceInitialized() / timeout); }
	public String getTitleForMeter() { return getName(); }
	
	public void end() { meter.startShutdown(); }
}