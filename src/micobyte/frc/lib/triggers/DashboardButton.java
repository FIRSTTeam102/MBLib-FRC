package micobyte.frc.lib.triggers;

import edu.wpi.first.networktables.NetworkTableInstance;
import micobyte.frc.lib.triggers.MBTrigger.MBButton;

/**
 * A {@link edu.wpi.first.wpilibj.buttons.Button Button} that is triggered by
 * one of the Dashboard buttons.
 */
public final class DashboardButton extends MBButton {
	/** DB/Button 0 */ public static final DashboardButton BTN_0 = new DashboardButton(0);
	/** DB/Button 1 */ public static final DashboardButton BTN_1 = new DashboardButton(1);
	/** DB/Button 2 */ public static final DashboardButton BTN_2 = new DashboardButton(2);
	/** DB/Button 3 */ public static final DashboardButton BTN_3 = new DashboardButton(3);
	
	/**
	 * Variable to hold which button to use.<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	protected final int id;
	
	/**
	 * Creates a {@link DashboardButton}
	 * @param id The button's ID
	 */
	private DashboardButton(int id) { this.id = id; }

	public boolean get() { return NetworkTableInstance.getDefault().getTable("SmartDashboard/DB").getEntry("Button " + id).getBoolean(false); }
}