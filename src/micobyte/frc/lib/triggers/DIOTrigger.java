package micobyte.frc.lib.triggers;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * A {@link edu.wpi.first.wpilibj.buttons.Trigger Trigger} that is tripped by a {@link DigitalInput digital signal}
 */
public class DIOTrigger extends MBTrigger {
	/** The {@link DigitalInput digital signal} */
	private final DigitalInput source;
	
	/**
	 * Creates the trigger
	 * @param source The {@link DigitalInput digital signal}
	 */
	public DIOTrigger(DigitalInput source) { this.source = source; }
	
	public boolean get() { return source.get(); }
}