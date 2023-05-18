package micobyte.frc.lib.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import micobyte.frc.lib.IStatusLogger;
import micobyte.frc.lib.MBLibUtil;

/**
 * A {@link IterativeRobot} with some extra features
 */
public abstract class BasicRobot extends IterativeRobot {
	/** The robot's name */
	private final String name;
	
	/** The robot's current status */
	private String status;
	
	/**
	 * Creates the robot, with the suggested name
	 */
	public BasicRobot() { name = MBLibUtil.getName(getClass(), "Robot"); }
	
	/**
	 * Creates the Robot, with the given name
	 * @param name The name
	 */
	public BasicRobot(String name) { this.name = name; }
	
	{ MBLibUtil.initializeLogWriterWithStandardEnding(getName()); }
	
	/**
	 * @return The robot's name
	 */
	public String getName() { return name; }
	
	/**
	 * Logs the robot's status
	 * @param status  The robot's new status
	 * @see {@link IStatusLogger}
	 */
	public void logStatus(String status) { MBLibUtil.log(getName() + " is now " + status + "."); this.status = status;  }
	
	/**
	 * @return The robot's status
	 */
	public String getStatus() { return status; }
	
	/**
	 * Creates an {@link IStatusLogger} from the robot
	 * @return The {@link IStatusLogger logger}
	 */
	public IStatusLogger createStatusLogger() { return this::logStatus; }
	
	public void robotPeriodic() {}
	public void robotInit() { logStatus("ready to go"); createOIAndSubsystems(); }
	
	/**
	 * Called when it is time to do such things as create the Operator Interface (OI) and {@link edu.wpi.first.wpilibj.command.Subsystem subsystems}
	 */
	protected abstract void createOIAndSubsystems();
	
	/**
	 * Called when {@link #autonomousInit() autonomous starts}
	 */
	protected void startAuto() {}
	
	/**
	 * Called when autonomous ends
	 */
	protected void endAuto() {}
	
	public void autonomousInit() { logStatus("in autonomous mode"); startAuto(); }
	public void teleopInit() { logStatus("in teleop mode"); endAuto(); }
	public void disabledInit() { logStatus("in disabled mode"); endAuto(); }
	public void testInit() { logStatus("in test mode"); endAuto(); }
	
	public void autonomousPeriodic() {}
	public void teleopPeriodic() {}
	public void disabledPeridic() {}
	public void testPeriodic() { LiveWindow.run(); }
}