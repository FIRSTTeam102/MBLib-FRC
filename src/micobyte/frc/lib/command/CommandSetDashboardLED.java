package micobyte.frc.lib.command;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.InstantCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A {@link edu.wpi.first.wpilibj.command.Command Command} to set one of the LEDs on the {@link SmartDashboard dashboard}
 */
public class CommandSetDashboardLED extends InstantCommand {
	/** Which LED to set */
	private final int id;
	/** {@link Supplier} that gives what to set the LED to */
	private final Supplier<Boolean> supp;
	
	/**
	 * Creates the {@link edu.wpi.first.wpilibj.command.Command Command}
	 * @param id The LED to set
	 * @param supp {@link Supplier} that gives what to set the LED to
	 */
	public CommandSetDashboardLED(int id, Supplier<Boolean> supp) { this.id = id; this.supp = supp; }
	
	public void initialize() { SmartDashboard.putBoolean("DB/Button " + id, supp.get()); }
}