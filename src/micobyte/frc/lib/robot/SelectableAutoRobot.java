package micobyte.frc.lib.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * A {@link CommandBasedRobot} with features for using a {@link SendableChooser} to select an autonomous mode
 */
public abstract class SelectableAutoRobot extends CommandBasedRobot {
	/** The {@link SendableChooser} chooser */
	private final SendableChooser<Command> autoModeChooser;
	
	/**
	 * Creates the robot, with the suggested name
	 */
	public SelectableAutoRobot() {}
	
	/**
	 * Creates the Robot, with the given name
	 * @param name The name
	 */
	public SelectableAutoRobot(String name) { super(name); }
	
	{ autoModeChooser = new SendableChooser<Command>(); }
	
	protected Command createAutoCommand() { return autoModeChooser.getSelected(); }
	public void robotInit() { super.robotInit(); addAutoModes(); }
	
	/**
	 * Called after {@link #createOIAndSubsystems()} to add all the {@link #addAutoMode(String, Command) autonomous modes}
	 */
	protected abstract void addAutoModes();
	
	/**
	 * Adds the given {@link Command} as an option for an autonomous mode
	 * @param name The name for this mode
	 * @param cmd The {@link Command}
	 */
	protected void addAutoMode(String name, Command cmd) { autoModeChooser.addObject(name, cmd); }
}