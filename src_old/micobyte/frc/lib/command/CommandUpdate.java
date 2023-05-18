package micobyte.frc.lib.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import micobyte.frc.lib.IUpdateable;
import micobyte.frc.lib.subsystem.UpdateablePIDSubsystem;
import micobyte.frc.lib.subsystem.UpdateableSubsystem;

/**
 * A {@link Command} that updates an {@link IUpdateable}.
 */
public class CommandUpdate extends Command {
	/** The {@link IUpdateable} to update */
	private final IUpdateable updateable;
	
	/**
	 * Creates a {@link CommandUpdate} from an {@link UpdateableSubsystem}, whose name provides the name field.
	 * @param uss The {@link UpdateableSubsystem}
	 */
	public CommandUpdate(UpdateableSubsystem uss) { this(uss, "Subsystem " + uss.getName()); }
	
	/**
	 * Creates a {@link CommandUpdate} from an {@link UpdateablePIDSubsystem}, whose name provides the name field.
	 * @param uss The {@link UpdateableSubsystem}
	 */
	public CommandUpdate(UpdateablePIDSubsystem uss) { this(uss, "PID Subsystem " + uss.getName()); }
	
	/**
	 * Creates a {@link CommandUpdate} from an {@link IUpdateable} and a name for it.
	 * @param updateable The {@link IUpdateable} to update
	 * @param name The name for the command
	 */
	public CommandUpdate(IUpdateable updateable, String name) {
		super("Update " + name);
		
		// Because a default command must require its Subsystem
		if(updateable instanceof Subsystem) requires((Subsystem)updateable);
		
		if(updateable == null) throw new NullPointerException("Updateable can't be null.");
		this.updateable = updateable;
	}
	
	public void execute() { updateable.update(); }
	protected boolean isFinished() { return updateable.shouldTerminate(); }
}