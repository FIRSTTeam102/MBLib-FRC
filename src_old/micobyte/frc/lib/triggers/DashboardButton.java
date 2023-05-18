package micobyte.frc.lib.triggers;

import edu.wpi.first.wpilibj.buttons.NetworkButton;

/**
 * A {@link edu.wpi.first.wpilibj.buttons.Button Button} that is triggered by
 * one of the Dashboard buttons.
 */
public final class DashboardButton extends NetworkButton {
	/** DB/Button 0 */ public static final DashboardButton BTN_0 = new DashboardButton(0);
	/** DB/Button 1 */ public static final DashboardButton BTN_1 = new DashboardButton(1);
	/** DB/Button 2 */ public static final DashboardButton BTN_2 = new DashboardButton(2);
	/** DB/Button 3 */ public static final DashboardButton BTN_3 = new DashboardButton(3);
	/** DB/Button 4 */ public static final DashboardButton BTN_4 = new DashboardButton(4);
	
	/**
	 * Creates a {@link DashboardButton}
	 * @param id The button's ID
	 */
	private DashboardButton(int id) { super("SmartDashboard/DB", "Button " + id); }
}