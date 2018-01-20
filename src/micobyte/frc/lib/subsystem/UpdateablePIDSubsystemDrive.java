package micobyte.frc.lib.subsystem;

import java.util.Arrays;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;

/**
 * An {@link UpdateablePIDSubsystem} that provides {@link IDriveSubsystem drive functionality}
 */
public abstract class UpdateablePIDSubsystemDrive extends UpdateablePIDSubsystem implements IDriveSubsystem {
	
	/** The Robot's {@link RobotDriveBase drive system} */
	protected final RobotDriveBase drive;
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.DifferentialDrive Differential drive}-based {@link UpdateablePIDSubsystemDrive}, for more than one motor on each side
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient@param left The left-side {@link SpeedController controllers}
	 * @param right The right-side {@link SpeedController controllers}
	 */
	public UpdateablePIDSubsystemDrive(double p, double i, double d, SpeedController[] left, SpeedController[] right) { this(p, i, d,
		new SpeedControllerGroup(left[0], Arrays.<SpeedController>copyOfRange(left, 1, left.length)),
		new SpeedControllerGroup(right[0], Arrays.<SpeedController>copyOfRange(right, 1, right.length))
	); }
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.KilloughDrive Killough drive}-based {@link UpdateablePIDSubsystemDrive}
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient@param left The left {@link SpeedController controller}
	 * @param right The right {@link SpeedController controller}
	 * @param back The back {@link SpeedController controller}
	 */
	public UpdateablePIDSubsystemDrive(double p, double i, double d, SpeedController left, SpeedController right, SpeedController back) { this(p, i, d, new SpeedController[] { left, right, back }); }
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.MecanumDrive Mecanum drive}-based {@link UpdateablePIDSubsystemDrive}
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient@param fL The front left {@link SpeedController controller}
	 * @param rL The rear left {@link SpeedController controller}
	 * @param fR The front right {@link SpeedController controller}
	 * @param rR The rear right {@link SpeedController controller}
	 */
	public UpdateablePIDSubsystemDrive(double p, double i, double d, SpeedController fL, SpeedController rL, SpeedController fR, SpeedController rR) { this(p, i, d, new SpeedController[] { fL, rL, fR, rR }); }
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.DifferentialDrive Differential drive}-based {@link UpdateablePIDSubsystemDrive}
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient@param left The left-side {@link SpeedController controller}
	 * @param right The right-side {@link SpeedController controller}
	 */
	public UpdateablePIDSubsystemDrive(double p, double i, double d, SpeedController left, SpeedController right) { this(p, i, d, new SpeedController[] { left, right }); }
	
	/**
	 * Creates an {@link UpdateablePIDSubsystemDrive} with the name "Drive Train"
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient@param controllers The {@link SpeedController controllers} to be used to {@link IDrivePIDSubsystem#createDrive(SpeedController[]) create} a type of {@link RobotDriveBase drive system}
	 */
	public UpdateablePIDSubsystemDrive(double p, double i, double d, SpeedController[] controllers) { this(p, i, d, IDriveSubsystem.createDrive(controllers)); }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystemDrive} that will use the given p, i and d values.
	 * @param name The name
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public UpdateablePIDSubsystemDrive(String name, double p, double i, double d, RobotDriveBase drive) { super(name, p, i, d); this.drive = drive; }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystemDrive} that will use the given p, i and d values.
	 * @param name The name
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param f The feed forward value
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public UpdateablePIDSubsystemDrive(String name, double p, double i, double d, double f, RobotDriveBase drive) { super(name, p, i, d, f); this.drive = drive; }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystemDrive} that will use the given p, i and d values. It will also
	 * space the time between PID loop calculations to be equal to the given period.
	 * @param name The name
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param f The feed forward value
	 * @param period The time (in seconds) between calculations
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public UpdateablePIDSubsystemDrive(String name, double p, double i, double d, double f, double period, RobotDriveBase drive) { super(name, p, i, d, f, period); this.drive = drive; }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystemDrive} that will use the given p, i and d values. It will use "Drive
	 * Train" as its name.
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public UpdateablePIDSubsystemDrive(double p, double i, double d, RobotDriveBase drive) { super(SUGGESTED_NAME, p, i, d); this.drive = drive; }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystemDrive} that will use the given p, i and d values. It will use "Drive
	 * Train"  as its name. It will also space the time between PID loop calculations to be equal to the given period.
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param period The time (in seconds) between calculations
	 * @param f The feed forward value
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public UpdateablePIDSubsystemDrive(double p, double i, double d, double period, double f, RobotDriveBase drive) { super(SUGGESTED_NAME, p, i, d, period, f); this.drive = drive; }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystemDrive} that will use the given p, i and d values. It will use "Drive
	 * Train" as its name. It will also space the time between PID loop calculations to be equal to the given period.
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param period The time (in seconds) between calculations
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public UpdateablePIDSubsystemDrive(double p, double i, double d, double period, RobotDriveBase drive) { super(SUGGESTED_NAME, p, i, d, period); this.drive = drive; }
	
	public RobotDriveBase getDrive() { return drive; }
	public void update() { updateDrive(); }
}