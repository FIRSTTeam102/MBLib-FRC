package micobyte.frc.lib.triggers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;

import micobyte.frc.lib.io.POVPosition;
import micobyte.frc.lib.triggers.MBTrigger.MBButton;

/**
 * A {@link edu.wpi.first.wpilibj.buttons.Button Button} that triggers from a POV switch being in a certain position
 */
public class POVButton extends MBButton {
	/**
	 * Which position for the POV switch<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	protected final POVPosition pos;
	
	/**
	 * Which {@link GenericHID HID device} and which POV switch to use<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	protected final int hid, pov;
	
	/**
	 * Creates the Button, for POV switch zero on the device
	 * @param hid Which {@link GenericHID HID device} to use
	 * @param pos What position the POV switch must be in
	 */
	public POVButton(GenericHID hid, POVPosition pos) { this(hid, 0, pos); }
	
	/**
	 * Creates the Button
	 * @param hid Which {@link GenericHID HID device} to use
	 * @param pov Which POV switch to use
	 * @param pos What position the POV switch must be in
	 */
	public POVButton(GenericHID hid, int pov, POVPosition pos) { this(hid.getPort(), pov, pos); }
	
	/**
	 * Creates the Button
	 * @param hid Which {@link GenericHID HID device} to use
	 * @param pov Which POV switch to use
	 * @param pos What position the POV switch must be in
	 */
	public POVButton(int hid, int pov, POVPosition pos) {
		if(hid < 0 || hid > 5) throw new IllegalArgumentException("Must provide valid HID ID");
		if(pov < 0 || pov > DriverStation.getInstance().getStickPOVCount(hid)) throw new IllegalArgumentException("Must provide a valid POV switch ID");
		if(pos == null) throw new IllegalArgumentException("Must provide a valid POV switch direction");
		
		this.hid = hid;
		this.pov = pov;
		this.pos = pos;
	}
	
	public boolean get() { return pos.accepts(hid, pov); }
}