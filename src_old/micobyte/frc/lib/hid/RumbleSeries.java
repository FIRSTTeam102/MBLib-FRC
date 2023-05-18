package micobyte.frc.lib.hid;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.GenericHID;
import micobyte.frc.lib.MBLibUtil;

/**
 * An object-oriented representation of a series of {@link Rumble rumbles} to be played in order, for a certain ammount of time
 */
public class RumbleSeries {
	/** The {@link Rumble rumbles} in order */
	private ArrayList<Rumble> rumbles = new ArrayList<Rumble>();
	/** The amount of time to play each rumble for */
	private ArrayList<Double> times = new ArrayList<Double>();
	
	/**
	 * Adds a {@link Rumble} to the series, and returns itself for ease of constructing
	 * @param rumble The {@link Rumble} to add
	 * @param time How long to add it for, in seconds
	 * @return itself, for ease of construction
	 */
	public RumbleSeries addRumble(Rumble rumble, double time) {
		rumbles.add(rumble);
		times.add(time);
		
		return this;
	}
	
	/**
	 * Adds a {@link Rumble#SOFT soft} {@link Rumble} for the specified amount of time
	 * @param time How long to add it for, in seconds
	 * @return itself, for ease of construction
	 */
	public RumbleSeries addSoft(double time) { return addRumble(Rumble.SOFT, time); }
	
	/**
	 * Adds a {@link Rumble#ROUGH rough} {@link Rumble} for the specified amount of time
	 * @param time How long to add it for, in seconds
	 * @return itself, for ease of construction
	 */
	public RumbleSeries addRough(double time) { return addRumble(Rumble.ROUGH, time); }
	
	/**
	 * Adds a {@link Rumble#BOTH both} {@link Rumble} for the specified amount of time
	 * @param time How long to add it for, in seconds
	 * @return itself, for ease of construction
	 */
	public RumbleSeries addBoth(double time) { return addRumble(Rumble.BOTH, time); }
	
	/**
	 * Adds a {@link Rumble#BREAK gap} for the specified amount of time
	 * @param time How long to add it for, in seconds
	 * @return itself, for ease of construction
	 */
	public RumbleSeries addBreak(double time) { return addRumble(Rumble.BREAK, time); }
	
	/**
	 * Plays the rumble to the specified {@link GenericHID joystick}, asynchronously
	 * @param joystick The {@link GenericHID joystick}
	 */
	public void play(GenericHID joystick) { play(joystick, true); }
	
	/**
	 * Plays the rumble to the specified {@link GenericHID joystick}, optionally asynchronously
	 * @param joystick The {@link GenericHID joystick}
	 * @param async Whether or not to play asynchronously
	 */
	public void play(GenericHID joystick, boolean async) {
		Runnable run = () -> {
			try {
				for(int i = 0; i < rumbles.size(); i++) {
					rumbles.get(i).play(joystick);
					Thread.sleep(Math.round(1000D * times.get(i)));
				}
				
				Rumble.BREAK.play(joystick);
			} catch(Exception e) {
				System.err.println("Error whilst playing rumble: ");
				e.printStackTrace();
			}
		};
		
		MBLibUtil.exec(run, async);
	}
	
	/**
	 * @return How long it would take to play this {@link RumbleSeries}
	 */
	public double getTotalTime() {
		double time = 0;
		for(Double d : times) time += d;
		
		return time;
	}
	
	/**
	 * The available types of rumble
	 */
	public static enum Rumble {
		/** What would normally be a soft {@link Rumble}, on the {@link GenericHID.RumbleType#kRightRumble right} side */
		SOFT(true, false),
		
		/** What would normally be a rough {@link Rumble}, on the {@link GenericHID.RumbleType#kLeftRumble left} side */
		ROUGH(false, true),
		
		/** Both a {@link #SOFT soft} and a {@link #ROUGH rough} {@link Rumble} at the same time */
		BOTH(true, true),
		
		/** A period of time without a {@link Rumble} */
		BREAK(false, false);
		
		/** Whether to {@link Rumble rumble} on the {@link #SOFT soft} side */
		private final boolean soft;
		
		/** Whether to {@link Rumble rumble} on the {@link #ROUGH rough} side */
		private final boolean rough;
		
		/**
		 * Enum constructor
		 * @param soft Whether to {@link Rumble rumble} on the {@link #SOFT soft} side 
		 * @param rough Whether to {@link Rumble rumble} on the {@link #ROUGH rough} side
		 */
		private Rumble(boolean soft, boolean rough) { this.soft = soft; this.rough = rough; }
		
		/**
		 * Plays this {@link Rumble rumble type} to the provided {@link GenericHID joystick}
		 * @param joystick The {@link GenericHID joystick}
		 */
		public void play(GenericHID joystick) {
			joystick.setRumble(GenericHID.RumbleType.kRightRumble, soft ? 1 : 0);
			joystick.setRumble(GenericHID.RumbleType.kLeftRumble, rough ? 1 : 0);
		}
	}
}