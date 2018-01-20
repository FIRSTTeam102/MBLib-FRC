package micobyte.frc.lib.triggers;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * A base class implemented to make using multiple {@link Trigger}s together in a logic gate chain-esque way much more practical
 */
public abstract class MBTrigger extends Trigger {
	
	/**
	 * @return This {@link Trigger}, as a {@link edu.wpi.first.wpilibj.buttons.Button Button}
	 */
	public MBButton asButton() { return MBButton.from(this); }
	
	/**
	 * @return A {@link Trigger} that will return the logical {@code NOT} of this {@link Trigger}
	 */
	public MBTrigger not() { return from(() -> !get()); }
	
	/**
	 * @param other The other {@link Trigger}
	 * @return A {@link Trigger} that will return the logical {@code OR} of this {@link Trigger} and the other
	 */
	public MBTrigger or(Trigger other) { return from(() -> get() || other.get()); }
	
	/**
	 * @param other The other {@link Trigger}
	 * @return A {@link Trigger} that will return the logical {@code AND} of this {@link Trigger} and the other
	 */
	public MBTrigger and(Trigger other) { return from(() -> get() && other.get()); }
	
	/**
	 * @param other The other {@link Trigger}
	 * @return A {@link Trigger} that will return the logical {@code XOR} (a.k.a. {@code NEQ}) of this {@link Trigger} and the other
	 */
	public MBTrigger xor(Trigger other) { return from(() -> get() != other.get()); }
	
	/**
	 * @param other The other {@link Trigger}
	 * @return A {@link Trigger} that will return the logical {@code XNOR} (a.k.a. {@code EQ}) of this {@link Trigger} and the other
	 */
	public MBTrigger xnor(Trigger other) { return from(() -> get() == other.get()); }
	
	/**
	 * @param supp The supplier that will provided the "is {@link Trigger}ed" value for the {@link Trigger}
	 * @return A {@link Trigger} that will return true when the given {@link Supplier} returns true
	 */
	public static MBTrigger from(Supplier<Boolean> supp) { return new MBTrigger() { public boolean get() { return supp.get(); } }; }
	
	/**
	 * @param trigger The other {@link Trigger}
	 * @return The given {@link Trigger}, wrapped as a {@link MBTrigger}
	 */
	public static MBTrigger from(Trigger trigger) { return from(trigger::get); }
	
	public static abstract class MBButton extends Button {
		/**
		 * @return A {@link Button} that will return the logical {@code NOT} of this {@link Button}
		 */
		public MBButton not() { return from(() -> !get()); }
		
		/**
		 * @param other The other {@link Button}
		 * @return A {@link Button} that will return the logical {@code OR} of this {@link Button} and the other
		 */
		public MBButton or(Button other) { return from(() -> get() || other.get()); }
		
		/**
		 * @param other The other {@link Button}
		 * @return A {@link Button} that will return the logical {@code AND} of this {@link Button} and the other
		 */
		public MBButton and(Button other) { return from(() -> get() && other.get()); }
		
		/**
		 * @param other The other {@link Button}
		 * @return A {@link Button} that will return the logical {@code XOR} (a.k.a. {@code NEQ}) of this {@link Button} and the other
		 */
		public MBButton xor(Button other) { return from(() -> get() != other.get()); }
		
		/**
		 * @param other The other {@link Button}
		 * @return A {@link Button} that will return the logical {@code XNOR} (a.k.a. {@code EQ}) of this {@link Button} and the other
		 */
		public MBButton xnor(Button other) { return from(() -> get() == other.get()); }
		
		/**
		 * @param supp The supplier that will provided the "is {@link Button}ed" value for the {@link Button}
		 * @return A {@link Button} that will return true when the given {@link Supplier} returns true
		 */
		public static MBButton from(Supplier<Boolean> supp) { return new MBButton() { public boolean get() { return supp.get(); } }; }
		
		/**
		 * @param trigger The {@link Trigger}
		 * @return The given {@link Trigger}, wrapped as a {@link MBButton}
		 */
		public static MBButton from(Trigger trigger) { return from(trigger::get); }
	}
}