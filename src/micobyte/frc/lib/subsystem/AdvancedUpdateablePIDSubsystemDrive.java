package micobyte.frc.lib.subsystem;

import java.util.Arrays;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import micobyte.frc.lib.MBLibUtil;

public abstract class AdvancedUpdateablePIDSubsystemDrive extends UpdateablePIDSubsystemDrive {
	
	/**
	 * Temporary holder-variable for the output of this {@link edu.wpi.first.wpilibj.PIDController PID-compatible} subsystem<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	protected transient double pidOutput = 0;
	
	/**
	 * Temporary state-variable for the {@link edu.wpi.first.wpilibj.PIDController PID loop}<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	protected transient boolean isPIDNew = false;
	
	/**
	 * Temporary holder-variable for the outputs from the last calculation of the drive outputs based on the {@link edu.wpi.first.wpilibj.PIDController PID loop}<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	protected transient double[] lastDriveOutputs = new double[] { 0, 0, 0, 0 };
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.DifferentialDrive Differential drive}-based {@link AdvancedUpdateablePIDSubsystemDrive}, for more than one motor on each side
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient@param left The left-side {@link SpeedController controllers}
	 * @param right The right-side {@link SpeedController controllers}
	 */
	public AdvancedUpdateablePIDSubsystemDrive(double p, double i, double d, SpeedController[] left, SpeedController[] right) { this(p, i, d,
		new SpeedControllerGroup(left[0], Arrays.<SpeedController>copyOfRange(left, 1, left.length)),
		new SpeedControllerGroup(right[0], Arrays.<SpeedController>copyOfRange(right, 1, right.length))
	); }
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.KilloughDrive Killough drive}-based {@link AdvancedUpdateablePIDSubsystemDrive}
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient@param left The left {@link SpeedController controller}
	 * @param right The right {@link SpeedController controller}
	 * @param back The back {@link SpeedController controller}
	 */
	public AdvancedUpdateablePIDSubsystemDrive(double p, double i, double d, SpeedController left, SpeedController right, SpeedController back) { this(p, i, d, new SpeedController[] { left, right, back }); }
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.MecanumDrive Mecanum drive}-based {@link AdvancedUpdateablePIDSubsystemDrive}
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient@param fL The front left {@link SpeedController controller}
	 * @param rL The rear left {@link SpeedController controller}
	 * @param fR The front right {@link SpeedController controller}
	 * @param rR The rear right {@link SpeedController controller}
	 */
	public AdvancedUpdateablePIDSubsystemDrive(double p, double i, double d, SpeedController fL, SpeedController rL, SpeedController fR, SpeedController rR) { this(p, i, d, new SpeedController[] { fL, rL, fR, rR }); }
	
	/**
	 * Creates a {@link edu.wpi.first.wpilibj.drive.DifferentialDrive Differential drive}-based {@link AdvancedUpdateablePIDSubsystemDrive}
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient@param left The left-side {@link SpeedController controller}
	 * @param right The right-side {@link SpeedController controller}
	 */
	public AdvancedUpdateablePIDSubsystemDrive(double p, double i, double d, SpeedController left, SpeedController right) { this(p, i, d, new SpeedController[] { left, right }); }
	
	/**
	 * Creates an {@link AdvancedUpdateablePIDSubsystemDrive} with the name "Drive Train"
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient@param controllers The {@link SpeedController controllers} to be used to {@link IDrivePIDSubsystem#createDrive(SpeedController[]) create} a type of {@link RobotDriveBase drive system}
	 */
	public AdvancedUpdateablePIDSubsystemDrive(double p, double i, double d, SpeedController[] controllers) { this(p, i, d, IDriveSubsystem.createDrive(controllers)); }
	
	/**
	 * Instantiates an {@link AdvancedUpdateablePIDSubsystemDrive} that will use the given p, i and d values.
	 * @param name The name
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public AdvancedUpdateablePIDSubsystemDrive(String name, double p, double i, double d, RobotDriveBase drive) { super(name, p, i, d, drive);}
	
	/**
	 * Instantiates an {@link AdvancedUpdateablePIDSubsystemDrive} that will use the given p, i and d values.
	 * @param name The name
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param f The feed forward value
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public AdvancedUpdateablePIDSubsystemDrive(String name, double p, double i, double d, double f, RobotDriveBase drive) { super(name, p, i, d, f, drive);}
	
	/**
	 * Instantiates an {@link AdvancedUpdateablePIDSubsystemDrive} that will use the given p, i and d values. It will also
	 * space the time between PID loop calculations to be equal to the given period.
	 * @param name The name
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param f The feed forward value
	 * @param period The time (in seconds) between calculations
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public AdvancedUpdateablePIDSubsystemDrive(String name, double p, double i, double d, double f, double period, RobotDriveBase drive) { super(name, p, i, d, f, period, drive);}
	
	/**
	 * Instantiates an {@link AdvancedUpdateablePIDSubsystemDrive} that will use the given p, i and d values. It will use "Drive
	 * Train" as its name.
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public AdvancedUpdateablePIDSubsystemDrive(double p, double i, double d, RobotDriveBase drive) { super(SUGGESTED_NAME, p, i, d, drive);}
	
	/**
	 * Instantiates an {@link AdvancedUpdateablePIDSubsystemDrive} that will use the given p, i and d values. It will use "Drive
	 * Train"  as its name. It will also space the time between PID loop calculations to be equal to the given period.
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param period The time (in seconds) between calculations
	 * @param f The feed forward value
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public AdvancedUpdateablePIDSubsystemDrive(double p, double i, double d, double period, double f, RobotDriveBase drive) { super(SUGGESTED_NAME, p, i, d, period, f, drive);}
	
	/**
	 * Instantiates an {@link AdvancedUpdateablePIDSubsystemDrive} that will use the given p, i and d values. It will use "Drive
	 * Train" as its name. It will also space the time between PID loop calculations to be equal to the given period.
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param period The time (in seconds) between calculations
	 * @param drive The {@link RobotDriveBase drive system} to be used
	 */
	public AdvancedUpdateablePIDSubsystemDrive(double p, double i, double d, double period, RobotDriveBase drive) { super(SUGGESTED_NAME, p, i, d, period, drive);}
	
	protected void usePIDOutput(double d) {
		pidOutput = d;
		isPIDNew = true;
	}
	
	public double[] driveAuto() {
		if(isPIDNew) {
			isPIDNew = false;
			lastDriveOutputs = pidDriveAuto(pidOutput);
		}
		
		if(!getPIDController().isEnabled()) {
			boolean setZero = false;
			for(double d : lastDriveOutputs) if(d != 0) setZero = true;
			
			if(setZero) {
				lastDriveOutputs = new double[] { 0, 0, 0, 0 };
				
				MBLibUtil.log(getName() + ": Set drive outputs to zero because PID loop was disabled and they were nonzero");
			}
		}
		
		return lastDriveOutputs;
	}
	
	/**
	 * Calculates the drive values for the robot, based on output of the {@link edu.wpi.first.wpilibj.PIDController PID loop}
	 * @param pidOutput The output from the {@link edu.wpi.first.wpilibj.PIDController PID loop}
	 * @return The robot drive outputs in order {lX, lY, rX, rY}
	 */
	protected double[] pidDriveAuto(double pidOutput) {
		double[] outputs = pidDriveAutoBaseIndependent(pidOutput);
		double speed = outputs[0], turn = outputs[1];
		
		if(isMecanumEsque()) return new double[] { 0, speed, turn, 0 };
		else return new double[] { 0, speed - turn, 0, speed + turn };
	}
	
	/**
	 * Calculates the output values for a "base-independent" drive system, from the output of the {@link edu.wpi.first.wpilibj.PIDController PID loop}
	 * @param pidOutput The output from the {@link edu.wpi.first.wpilibj.PIDController PID loop}
	 * @return The robot drive outputs in order {forewardSpeed, turnSpeed}
	 */
	protected abstract double[] pidDriveAutoBaseIndependent(double pidOutput);
}