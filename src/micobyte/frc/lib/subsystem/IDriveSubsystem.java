package micobyte.frc.lib.subsystem;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.*;
import micobyte.frc.lib.CommonIDs;
import micobyte.frc.lib.MBLibUtil;

/**
 * An interface to represent a {@link edu.wpi.first.wpilibj.command.Subsystem Subsystem} that drives a robot
 */
public interface IDriveSubsystem {
	
	/** A name suggested for any {@link edu.wpi.first.wpilibj.command.Subsystem Subsystem} that implements this */
	public static final String SUGGESTED_NAME = "Drive Train";
	
	/** @return The {@link RobotDriveBase drive} that the subsystem uses */
	public RobotDriveBase getDrive();
	
	/** @return The {@link GenericHID HID devices} use to drive the robot during teleop period */
	public GenericHID[] getDriveHIDDevices();
	
	/** @return The value to be used as a {@link MBLibUtil#getAxisWithDeadband(GenericHID, int, double) dead-band} for the {@link #getDriveHIDDevices() drive devices} */
	public double getDriveDeadband();
	
	/** @return true if the {@link RobotDriveBase drive system} is Mecanum-esque, e.g. is controlled with a method that takes a X speed, Y speed, and rotation speed. */
	public default boolean isMecanumEsque() { return getDrive() instanceof MecanumDrive || getDrive() instanceof KilloughDrive; }
	
	/** @return Whether each of the four Joystick drive axes should be inverted */
	public default boolean[] getInverts() { return new boolean[] { false, false, false, false }; }
	
	/** Update delegator method for the teleop versus autonomous periods */
	public default void updateDrive() {
		double[] vals;
		if(RobotState.isOperatorControl()) vals = driveTeleop();
		else if(RobotState.isAutonomous()) vals = driveAuto();
		else return;
		
		drive(vals);
	}
	
	/**
	 * Called during autonomous period to drive the robot
	 * @return The robot drive outputs in order {lX, lY, rX, rY}
	 */
	public default double[] driveAuto() { return new double[] { 0, 0, 0, 0 }; }
	
	/**
	 * Called during teleop period to get the robot drive ouputs
	 * @return The robot drive outputs in order {lX, lY, rX, rY}
	 */
	public default double[] driveTeleop() {
		GenericHID[] controllers = getDriveHIDDevices();
		double deadband = getDriveDeadband();
		
		double lX, lY, rX, rY;
		if(controllers.length == 1) {
			lX = MBLibUtil.getAxisWithDeadband(controllers[0], CommonIDs.Gamepad.AXIS_LEFT_X, deadband);
			lY = MBLibUtil.getAxisWithDeadband(controllers[0], CommonIDs.Gamepad.AXIS_LEFT_Y, deadband);
			rX = MBLibUtil.getAxisWithDeadband(controllers[0], CommonIDs.Gamepad.AXIS_RIGHT_X, deadband);
			rY = MBLibUtil.getAxisWithDeadband(controllers[0], CommonIDs.Gamepad.AXIS_RIGHT_Y, deadband);
		} else if(controllers.length == 2) {
			lX = MBLibUtil.getAxisWithDeadband(controllers[0], CommonIDs.Joystick.AXIS_X, deadband);
			lY = MBLibUtil.getAxisWithDeadband(controllers[0], CommonIDs.Joystick.AXIS_Y, deadband);
			rX = MBLibUtil.getAxisWithDeadband(controllers[1], CommonIDs.Joystick.AXIS_X, deadband);
			rY = MBLibUtil.getAxisWithDeadband(controllers[1], CommonIDs.Joystick.AXIS_Y, deadband);
		} else throw new IllegalArgumentException("Must be either 1 (gamepad-drive) or 2 (dual-joystick drive) HID devices");
		
		return new double[] { lX, lY, rX, rY };
	}
	
	/**
	 * Drives the robot based on the given stick-inputs
	 * @param vals The robot drive stick-inputs in order {lX, lY, rX, rY}
	 */
	public default void drive(double[] vals) { drive(vals[0], vals[1], vals[2], vals[3]); }
	
	/**
	 * Drives the robot, based on the given stick-inputs
	 * @param lX The left X value
	 * @param lY The left Y value
	 * @param rX The right X value
	 * @param rY The right Y value
	 */
	public default void drive(double lX, double lY, double rX, double rY) {
		RobotDriveBase drive = getDrive();
		
		boolean[] inverts = getInverts();
		if(inverts[0]) lX = -lX;
		if(inverts[1]) lY = -lY;
		if(inverts[2]) rX = -rX;
		if(inverts[3]) rY = -rY;
		
		if(drive instanceof DifferentialDrive) ((DifferentialDrive)drive).tankDrive(lY, rY);
		else if(drive instanceof MecanumDrive) ((MecanumDrive)drive).driveCartesian(lX, lY, rX);
		else if(drive instanceof KilloughDrive) ((KilloughDrive)drive).driveCartesian(lX, lY, rX);
		else throw new IllegalArgumentException("Must be either Differential, Mecanum, or Killough drive");
	}
	
	/**
	 * Utility method to create a {@link RobotDriveBase drive system}, assuming what kind by how many {@link SpeedController controllers} are provided
	 * @param controllers The {@link SpeedController controllers}
	 * @return The {@link RobotDriveBase drive system}
	 * @throws IllegalArgumentException If the number of {@link SpeedController controllers} provided does not work
	 */
	public static RobotDriveBase createDrive(SpeedController[] controllers) {
		if(controllers.length == 2) return new DifferentialDrive(controllers[0], controllers[1]);
		else if(controllers.length == 3) return new KilloughDrive(controllers[0], controllers[1], controllers[2]);
		else if(controllers.length == 4) return new MecanumDrive(controllers[0], controllers[1], controllers[2], controllers[3]);
		else throw new IllegalArgumentException("Must be 2 (Differential), 3 (Killough), or 4 (Mecanum) controllers");
	}
}