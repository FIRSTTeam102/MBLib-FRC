package micobyte.frc.lib.command;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A {@link edu.wpi.first.wpilibj.command.Command Command} to set one of the LEDs on the {@link SmartDashboard dashboard}
 */
public class CommandSetDriverStationLED extends InstantCommand {
	/** Which LED to set */
	private final int id;
	/** What to set the LED to */
	private final boolean value;
	
	/**
	 * Creates the {@link edu.wpi.first.wpilibj.command.Command Command}
	 * @param id The LED to set
	 * @param value What to set the LED to
	 */
	public CommandSetDriverStationLED(int id, boolean value) { this.id = id; this.value = value; }
	
	public void initialize() { SmartDashboard.putBoolean("DB/Button " + id, value); }
}