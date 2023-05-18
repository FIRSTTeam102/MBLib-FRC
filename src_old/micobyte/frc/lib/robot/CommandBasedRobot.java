package micobyte.frc.lib.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * A {@link BasicRobot} with all the code for executing {@link Command commands} pre-baked in, and also features for a {@link Command}-based autonomous mode
 */
public abstract class CommandBasedRobot extends BasicRobot {
	/** The currently executing autonomous {@link Command command} */
	protected Command autoCommand;
	
	/**
	 * Creates the robot, with the suggested name
	 */
	public CommandBasedRobot() {}
	
	/**
	 * Creates the Robot, with the given name
	 * @param name The name
	 */
	public CommandBasedRobot(String name) { super(name); }
	
	/**
	 * Called by {@link #startAuto()} to create the {@link Command} to be executed for autonomous mode, or {@code null} for none.
	 * @return The new autonomous {@link Command command}
	 */
	protected Command createAutoCommand() { return null; }
	
	protected void startAuto() {
		autoCommand = createAutoCommand();
		
		if(autoCommand != null) {
			autoCommand.start();
			
			logStatus("starting autonomous \"" + autoCommand.getName() + "\"");
		} else logStatus("not executing an autonomous mode because it has none");
	}
	
	protected void endAuto() {
		if(autoCommand != null) {
			autoCommand.cancel();
			autoCommand = null;
			
			logStatus("exiting autonomous mode");
		}
	}
	
	public void autonomousPeriodic() { super.autonomousPeriodic(); Scheduler.getInstance().run(); }
	public void teleopPeriodic() { super.teleopPeriodic(); Scheduler.getInstance().run(); }
	public void disabledPeriodic() { super.disabledPeriodic(); Scheduler.getInstance().run(); }
	public void testPeriodic() { super.testPeriodic(); Scheduler.getInstance().run(); }
}