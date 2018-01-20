package micobyte.frc.lib.triggers;

import edu.wpi.first.wpilibj.CounterBase;

/**
 * A {@link edu.wpi.first.wpilibj.buttons.Trigger Trigger} that is tripped when a {@link CounterBase counter} overflows a certain value.
 */
public class CounterTrigger extends MBTrigger {
	/** The value to count up to */
	private final int minValue;
	/** The {@link CounterBase counter}  */
	private final CounterBase counter;
	
	/**
	 * Creates the trigger
	 * @param minValue The value to count up to
	 * @param counter The {@link CounterBase counter}
	 */
	public CounterTrigger(int minValue, CounterBase counter) { this.minValue = minValue; this.counter = counter; }
	
	public boolean get() {
		if(counter.get() >= minValue) {
			counter.reset();
			return true;
		}
		
		return false;
	}
}