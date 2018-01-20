package micobyte.frc.lib.triggers;

import java.util.function.Predicate;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * A {edu.wpi.first.wpilibj.buttons.Trigger Trigger} that is tripped by an {@link AnalogInput} being within a given range
 */
public class AIOTrigger extends MBTrigger {
	/** The {@link AnalogInput input} */
	private final AnalogInput input;
	/** The {@link Predicate selector} */
	private final Predicate<Integer> selector;
	
	/**
	 * Creates the trigger, for values accepted by the given {@link Predicate selector}
	 * @param input The {@link AnalogInput input}
	 * @param selector The {@link Predicate selector}
	 */
	public AIOTrigger(AnalogInput input, Predicate<Integer> selector) { this.input = input; this.selector = selector; }
	
	public boolean get() { return selector.test(input.getValue()); }
	
	/**
	 * Creates a trigger that returns true for values such that {@code max > val > min}
	 * @param input The {@link AnalogInput input}
	 * @param min The min. value
	 * @param max The max. value
	 * @return The trigger
	 */
	public static AIOTrigger forRange(AnalogInput input, int min, int max) { return new AIOTrigger(input, i -> i > min && i < max); }
	
	/**
	 * Creates a trigger that returns true for values such that {@code max > val}
	 * @param input The {@link AnalogInput input}
	 * @param max The max. value
	 * @return The trigger
	 */
	public static AIOTrigger forLessThan(AnalogInput input, int max) { return new AIOTrigger(input, i -> i < max); }
	
	/**
	 * Creates a trigger that returns true for values such that {@code val > min}
	 * @param input The {@link AnalogInput input}
	 * @param min The min. value
	 * @return The trigger
	 */
	public static AIOTrigger forGreaterThan(AnalogInput input, int min) { return new AIOTrigger(input, i -> i > min); }
	
	/**
	 * Creates a trigger that returns true for values such that {@code val == eq}
	 * @param input The {@link AnalogInput input}
	 * @param eq The value to equal to
	 * @return The trigger
	 */
	public static AIOTrigger forEqualTo(AnalogInput input, int eq) { return new AIOTrigger(input, ((Integer)eq)::equals); }
}