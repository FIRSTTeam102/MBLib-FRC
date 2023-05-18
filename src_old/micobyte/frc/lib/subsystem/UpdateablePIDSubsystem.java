package micobyte.frc.lib.subsystem;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import micobyte.frc.lib.IUpdateable;
import micobyte.frc.lib.command.CommandUpdate;

/**
 * A {@link PIDSubsystem} that implements {@link IUpdateable}, for ease of automatic updates.
 */
public abstract class UpdateablePIDSubsystem extends PIDSubsystem implements IUpdateable {
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystem} that will use the given p, i and d values.
	 * @param name The name
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 */
	public UpdateablePIDSubsystem(String name, double p, double i, double d) { super(name, p, i, d); }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystem} that will use the given p, i and d values.
	 * @param name The name
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param f The feed forward value
	 */
	public UpdateablePIDSubsystem(String name, double p, double i, double d, double f) { super(name, p, i, d, f); }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystem} that will use the given p, i and d values. It will also
	 * space the time between PID loop calculations to be equal to the given period.
	 * @param name The name
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param f The feed forward value
	 * @param period The time (in seconds) between calculations
	 */
	public UpdateablePIDSubsystem(String name, double p, double i, double d, double f, double period) { super(name, p, i, d, f, period); }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystem} that will use the given p, i and d values. It will use the
	 * class name as its name.
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 */
	public UpdateablePIDSubsystem(double p, double i, double d) { super(p, i, d); }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystem} that will use the given p, i and d values. It will use the
	 * class name as its name. It will also space the time between PID loop calculations to be equal to the given period.
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param period
	 * @param f The feed forward value
	 */
	public UpdateablePIDSubsystem(double p, double i, double d, double period, double f) { super(p, i, d, period, f); }
	
	/**
	 * Instantiates an {@link UpdateablePIDSubsystem} that will use the given p, i and d values. It will use the
	 * class name as its name. It will also space the time between PID loop calculations to be equal to the given period.
	 * @param p The proportional coefficient
	 * @param i The integral coefficient
	 * @param d The derivative coefficient
	 * @param period The time (in seconds) between calculations
	 */
	public UpdateablePIDSubsystem(double p, double i, double d, double period) { super(p, i, d, period); }
	
	
	protected void initDefaultCommand() { setDefaultCommand(new CommandUpdate(this)); }
}