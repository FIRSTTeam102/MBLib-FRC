package micobyte.frc.lib.hid;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class SuperGlove {
	public static final int NUM_AXES = 6;
	public static final int NUM_BUTTONS = 4;
	
	public static final int ID_1ST_FINGER = 0;
	public static final int ID_2ND_FINGER = 1;
	public static final int ID_3RD_FINGER = 2;
	public static final int ID_4TH_FINGER = 3;
	
	public static final int AXIS_ONLY_ID_PITCH = 4;
	public static final int AXIS_ONLY_ID_YAW = 5;
	
	public static final int BTN_ONLY_ID_ARE_BTNS_CON = 4;
	
	protected final int port;
	
	public SuperGlove(int port) { this.port = port; }
	
	protected double getAxis(int num) { return DriverStation.getInstance().getStickAxis(port, num); }
	protected boolean getButton(int num) { return DriverStation.getInstance().getStickButton(port, (byte)num); }
	
	public double getFirstFingerAxis() { return getAxis(ID_1ST_FINGER); }
	public double getSecondFingerAxis() { return getAxis(ID_2ND_FINGER); }
	public double getThirdFingerAxis() { return getAxis(ID_3RD_FINGER); }
	public double getFourthFingerAxis() { return getAxis(ID_4TH_FINGER); }
	
	public double getPitch() { return getAxis(AXIS_ONLY_ID_PITCH); }
	public double getYaw() { return getAxis(AXIS_ONLY_ID_YAW); }
	
	public boolean getFirstFingerButton() { return getButton(ID_1ST_FINGER); }
	public boolean getSecondFingerButton() { return getButton(ID_2ND_FINGER); }
	public boolean getThirdFingerButton() { return getButton(ID_3RD_FINGER); }
	public boolean getFourthFingerButton() { return getButton(ID_4TH_FINGER); }
	
	public boolean getAreButtonsConnected() { return getButton(BTN_ONLY_ID_ARE_BTNS_CON); }
	
	public JoystickButton getFirstFingerTrigger() { return new SGJoystickButton(ID_1ST_FINGER); }
	public JoystickButton getSecondFingerTrigger() { return new SGJoystickButton(ID_2ND_FINGER); }
	public JoystickButton getThirdFingerTrigger() { return new SGJoystickButton(ID_3RD_FINGER); }
	public JoystickButton getFourthFingerTrigger() { return new SGJoystickButton(ID_4TH_FINGER); }
	
	private final class SGJoystickButton extends JoystickButton {
		private final int buttonNumber;
		
		private SGJoystickButton(int buttonNumber) { super(null, buttonNumber); this.buttonNumber = buttonNumber; }
		
		public boolean get() { return getButton(buttonNumber); }
	}
}