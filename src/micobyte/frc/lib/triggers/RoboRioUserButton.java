package micobyte.frc.lib.triggers;

import edu.wpi.first.wpilibj.RobotController;
import micobyte.frc.lib.triggers.MBTrigger.MBButton;

/**
 * A {@link edu.wpi.first.wpilibj.buttons.Button Button} that is triggered by the {@link RobotController#getUserButton() RoboRIO USER Button}
 */
public final class RoboRioUserButton extends MBButton {
	/** Singleton instance*/ public static final RoboRioUserButton INSTANCE = new RoboRioUserButton();
	
	private RoboRioUserButton() {}
	
	public boolean get() { return RobotController.getUserButton(); }
}