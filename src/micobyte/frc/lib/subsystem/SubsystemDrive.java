package micobyte.frc.lib.subsystem;

import java.util.Arrays;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;

/**
 * An {@link Subsystem} that provides {@link IDriveSubsystem drive functionality}
 */
public abstract class SubsystemDrive extends Subsystem implements IDriveSubsystem {
	
	/** The Robot's {@link RobotDriveBase drive system} */
	protected final RobotDriveBase drive;
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.DifferentialDrive Differential drive}-based {@link SubsystemDrive}, for more than one motor on each side
	 * @param left The left-side {@link SpeedController controllers}
	 * @param right The right-side {@link SpeedController controllers}
	 */
	public SubsystemDrive(SpeedController[] left, SpeedController[] right) { this(
		new SpeedControllerGroup(left[0], Arrays.<SpeedController>copyOfRange(left, 1, left.length)),
		new SpeedControllerGroup(right[0], Arrays.<SpeedController>copyOfRange(right, 1, right.length))
	); }
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.KilloughDrive Killough drive}-based {@link SubsystemDrive}
	 * @param left The left {@link SpeedController controller}
	 * @param right The right {@link SpeedController controller}
	 * @param back The back {@link SpeedController controller}
	 */
	public SubsystemDrive(SpeedController left, SpeedController right, SpeedController back) { this(new SpeedController[] { left, right, back }); }
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.MecanumDrive Mecanum drive}-based {@link SubsystemDrive}
	 * @param fL The front left {@link SpeedController controller}
	 * @param rL The rear left {@link SpeedController controller}
	 * @param fR The front right {@link SpeedController controller}
	 * @param rR The rear right {@link SpeedController controller}
	 */
	public SubsystemDrive(SpeedController fL, SpeedController rL, SpeedController fR, SpeedController rR) { this(new SpeedController[] { fL, rL, fR, rR }); }
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.DifferentialDrive Differential drive}-based {@link SubsystemDrive}
	 * @param left The left-side {@link SpeedController controller}
	 * @param right The right-side {@link SpeedController controller}
	 */
	public SubsystemDrive(SpeedController left, SpeedController right) { this(new SpeedController[] { left, right }); }
	
	/**
	 * Creates an {@link SubsystemDrive} with the name "Drive Train"
	 * @param controllers The {@link SpeedController controllers} to be used to {@link IDriveSubsystem#createDrive(SpeedController[]) create} a type of {@link RobotDriveBase drive system}
	 */
	public SubsystemDrive(SpeedController[] controllers) { this(IDriveSubsystem.createDrive(controllers)); }
	
	/**
	 * Creates an {@link SubsystemDrive} with name "Drive Train"
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public SubsystemDrive(RobotDriveBase drive) { this(SUGGESTED_NAME, drive); }
	
	/**
	 * Creates an {@link SubsystemDrive} with a given name.
	 * @param name The name of the {@link SubsystemDrive}
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public SubsystemDrive(String name, RobotDriveBase drive) { super(name); this.drive = drive; }
	
	public RobotDriveBase getDrive() { return drive; }
	public void periodic() { updateDrive(); }
}