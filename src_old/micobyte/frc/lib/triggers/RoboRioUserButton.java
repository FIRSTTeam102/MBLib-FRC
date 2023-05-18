package micobyte.frc.lib.triggers;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * A {@link Button} that is triggered by the {@link Utility#getUserButton RoboRIO USER Button}
 */
public final class RoboRioUserButton extends Button {
	/** Singleton instance*/ public static final RoboRioUserButton INSTANCE = new RoboRioUserButton();
	
	private RoboRioUserButton() {}
	
	public boolean get() { return Utility.getUserButton(); }
}