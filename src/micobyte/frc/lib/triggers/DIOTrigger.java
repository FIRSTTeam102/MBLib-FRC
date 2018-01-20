package micobyte.frc.lib.triggers;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * A {@link edu.wpi.first.wpilibj.buttons.Trigger Trigger} that is tripped by a {@link DigitalInput digital signal}, with active-low support.
 */
public class DIOTrigger extends MBTrigger {
	/** The {@link DigitalInput digital signal} */
	private final DigitalInput source;
	/** Whether or not to be in active-low mode */
	private final boolean activeLow;
	
	/**
	 * Creates the trigger in active-high mode
	 * @param source The {@link DigitalInput digital signal}
	 */
	public DIOTrigger(DigitalInput source) { this(source, false); }
	
	/**
	 * Creates the trigger
	 * @param source The {@link DigitalInput digital signal}
	 * @param activeLow Whether or not to be in active-low mode
	 */
	public DIOTrigger(DigitalInput source, boolean activeLow) { this.source = source; this.activeLow = activeLow; }
	
	public boolean get() { return source.get() ^ activeLow; }
}