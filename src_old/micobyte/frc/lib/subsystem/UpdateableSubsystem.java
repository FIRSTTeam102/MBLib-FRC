package micobyte.frc.lib.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;
import micobyte.frc.lib.IUpdateable;
import micobyte.frc.lib.command.CommandUpdate;

/**
 * A {@link Subsystem} that implements {@link IUpdateable}, for ease of automatic updates.
 */
public abstract class UpdateableSubsystem extends Subsystem implements IUpdateable {
	
	/**
	 * Creates an {@link UpdateableSubsystem} with a given name.
	 * @param name The name of the {@link UpdateableSubsystem}
	 */
	public UpdateableSubsystem(String name) { super(name); }
	
	/**
	 * Creates an {@link UpdateableSubsystem}. This will set the name to the name of the class.
	 */
	public UpdateableSubsystem() { super(); }
	
	protected void initDefaultCommand() { setDefaultCommand(new CommandUpdate(this)); }
}