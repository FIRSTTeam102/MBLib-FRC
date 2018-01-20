package micobyte.frc.lib.io;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import micobyte.frc.lib.IUpdateable;
import micobyte.frc.lib.MBLibUtil;

import micobyte.frc.lib.command.CommandUpdate;

/**
 * A class that draws a linear meter to the Strings section of the {@link SmartDashboard dashboard}.<br>
 * Example: <code>Progress: [-------+--]</code>
 */
public class TextMeter {
	/** Slots available to use for the meter */
	private static boolean[] slotsAvailable = { true, true, true, true, true };
	
	/** Min. and max. values for the meter */
	private final float min, max;
	/** Meter's current value */
	private float currValue;
	
	/** Which DB/String it goes into */
	private final int stringSlot;
	/** Title of the meter */
	private final String title;
	
	/** Shut down stages */
	private boolean[] shouldShutDown = { false, false };
	
	/**
	 * Creates the {@link TextMeter}
	 * @param min The min. value for the meter
	 * @param max The max. value for the meter
	 * @param stringSlot Which DB/String it goes into
	 * @param title Title of the meter
	 */
	public TextMeter(float min, float max, int stringSlot, String title) {
		this.min = min;
		this.max = max;
		currValue = min;
		
		this.stringSlot = stringSlot;
		this.title = title;
		
		slotsAvailable[stringSlot] = false;
	}
	
	/**
	 * Sets the value of the meter
	 * @param newValue The new value for the meter
	 */
	public void setValue(float newValue) { currValue = newValue; }
	
	/** Draws the meter */
	public void draw() {
		int size = 33;
		String str = "";
		
		if(!shouldShutDown[0]) {
			if(title != null && title.trim().length() > 0) {
				str += title + ": ";
				size -= title.length();
			}
			
			str += '[';
			int posOfPlus = Math.round((currValue - min) / (max - min) * (float)size);
			
			for(int i = 0; i < size; i++) {
				if(i == posOfPlus) str += '+';
				else str += '-';
			}
			
			str += ']';
		}
		
		SmartDashboard.putString("DB/String " + stringSlot, str);
	}
	
	/**
	 * Begins the shutdown process.
	 */
	public void startShutdown() {
		shouldShutDown[0] = true;
		MBLibUtil.sleep(.1F);
		shouldShutDown[1] = true;
		
		slotsAvailable[stringSlot] = true;
	}
	
	/**
	 * @return The lowest available string slot, or -1 if none exist
	 */
	public static int getLowestAvailableSlot() {
		for(int i = 0; i < slotsAvailable.length; i++) if(slotsAvailable[i]) return i;
		return -1;
	}
	
	/**
	 * Sets the value of the meter, then draws it
	 * @param newValue The new value for the meter
	 */
	public void setAndDraw(float newValue) { setValue(newValue); draw(); }
	
	/**
	 * Creates a {@link IUpdateable} for use with auto-updating the meter
	 * @param valueSupp A {@link Supplier} for the meter's values
	 * @return The {@link IUpdateable}
	 */
	public IUpdateable createUpdateInterface(Supplier<Float> valueSupp) { return () -> setAndDraw(valueSupp.get()); }
	
	/**
	 * Creates a {@link IUpdateable} for use with auto-updating the meter, with termination built in
	 * @param valueSupp A {@link Supplier} for the meter's values
	 * @return The {@link IUpdateable}
	 */
	public IUpdateable createTerminateableUpdateInterface(Supplier<Float> valueSupp) { return IUpdateable.withTermination(createUpdateInterface(valueSupp), () -> shouldShutDown[1]); }
	
	/**
	 * Creates a {@link CommandUpdate} from the {@link #createUpdateInterface(Supplier)} method
	 * @param valueSupp A {@link Supplier} for the meter's values
	 * @return The {@link CommandUpdate}
	 */
	public CommandUpdate createUpdateCommand(Supplier<Float> valueSupp) { return new CommandUpdate(createTerminateableUpdateInterface(valueSupp), "TextMeter " + (title == null ? "(unnamed)" : title) + " (slot #" + stringSlot + ")"); }
	
	/**
	 * Starts a {@link CommandUpdate} from the {@link #createUpdateCommand(Supplier)} method
	 * @param valueSupp A {@link Supplier} for the meter's values
	 */
	public void createAndStartUpdateCommand(Supplier<Float> valueSupp) { createUpdateCommand(valueSupp).start(); }
	
	/**
	 * Creates a {@link TextMeter}, and starts it with a {@link CommandUpdate}
	 * @param min The min. value for the meter
	 * @param max The max. value for the meter
	 * @param stringSlot Which DB/String it goes into
	 * @param title Title of the meter
	 * @param valueSupp A {@link Supplier} for the meter's values
	 * @return The TextMeter
	 */
	public static TextMeter createAndStart(float min, float max, int stringSlot, String title, Supplier<Float> valueSupp) {
		TextMeter tm = new TextMeter(min, max, stringSlot, title);
		tm.createAndStartUpdateCommand(valueSupp);
		
		return tm;
	}
	
	/**
	 * Creates a {@link TextMeter}, and starts it with a {@link CommandUpdate}, using the next available String slot
	 * @param min The min. value for the meter
	 * @param max The max. value for the meter
	 * @param title Title of the meter
	 * @param valueSupp A {@link Supplier} for the meter's values
	 * @return The TextMeter
	 */
	public static TextMeter createAndStart(float min, float max, String title, Supplier<Float> valueSupp) {
		int stringSlot = getLowestAvailableSlot();
		
		if(stringSlot == -1) {
			MBLibUtil.log("Error: out of slots for a TextMeter!");
			return null;
		}
		
		return createAndStart(min, max, stringSlot, title, valueSupp);
	}
	
	/**
	 * Creates a {@link TextMeter}, and starts it with a {@link CommandUpdate}, using the next available String slot, with a min. value of zero.
	 * @param max The max. value for the meter
	 * @param title Title of the meter
	 * @param valueSupp A {@link Supplier} for the meter's values
	 * @return The TextMeter
	 */
	public static TextMeter createAndStart(float max, String title, Supplier<Float> valueSupp) { return createAndStart(0, max, title, valueSupp); }
	
	/**
	 * Creates a {@link TextMeter}, and starts it with a {@link CommandUpdate}, using the next available String slot, with a min. value of zero, and a max. value of one
	 * @param title Title of the meter
	 * @param valueSupp A {@link Supplier} for the meter's values
	 * @return The TextMeter
	 */
	public static TextMeter createAndStart(String title, Supplier<Float> valueSupp) { return createAndStart(1, title, valueSupp); }
	
	/**
	 * Creates an unnamed {@link TextMeter}, and starts it with a {@link CommandUpdate}, using the next available String slot, with a min. value of zero, and a max. value of one
	 * @param valueSupp A {@link Supplier} for the meter's values
	 * @return The TextMeter
	 */
	public static TextMeter createAndStart(Supplier<Float> valueSupp) { return createAndStart(null, valueSupp); }
	
	/**
	 * An interface which represents something that may be metered by a {@link TextMeter}.
	 */
	public static interface IMeterable {
		/** @return The progress, between zero and one (inclusive). */
		float getProgress();
		
		/** @return The name for the meter */
		String getTitleForMeter();
		
		/** Creates a {@link TextMeter} from the {@link IMeterable} instance, and starts it */
		default TextMeter createAndStart() { return TextMeter.createAndStart(getTitleForMeter(), () -> getProgress()); }
	}
}