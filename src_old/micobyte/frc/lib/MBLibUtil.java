package micobyte.frc.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A collection of Utility methods that are (mostly) intended to be internal to the library
 */
public final class MBLibUtil {
	/**
	 * The {@link BufferedWriter writer} for the log file<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	private static BufferedWriter logWriter;
	
	/**
	 * The method to initialize the log writer<br>
	 * <b>This is an internal method. It should not be called by the end user.</b>
	 * @param logFileName The filename for the log file
	 */
	public static void initializeLogWriter(String logFileName) {
		if(logWriter != null) return;
		
		try {
			File logDir = new File(new File(System.getProperty("user.home")), "robot-logs");
			if(!logDir.exists()) logDir.mkdirs();
			if(!logDir.isDirectory()) throw new IOException("Log directory is not a directory?");
			
			File logFile = new File(logDir, logFileName);
			if(logFile.exists()) throw new IOException("Log file already exists?");
			logFile.createNewFile();
			
			logWriter = new BufferedWriter(new FileWriter(logFile));
		} catch(Exception e) {
			System.err.println("Error whilst opening log writer: ");
			e.printStackTrace();
		}
	}
	
	/**
	 * The method to initialize the log writer, with the log file name (prefix)-(date-time string).log<br>
	 * <b>This is an internal method. It should not be called by the end user.</b>
	 * @param prefix The prefix for the log file name
	 */
	public static void initializeLogWriterWithStandardEnding(String prefix) { initializeLogWriter(prefix + "-" + MBLibUtil.getDateTimeString() + ".log"); }
	
	/**
	 * Gets the current date and time string, formatted as yyyy-MM-dd-HH-mm-ss-a-z
	 * @return The date-time string
	 */
	public static String getDateTimeString() { return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-a-z").format(new Date()); }
	
	/**
	 * Writes the given string to the log file with the date-time string as a prefix
	 * @param msg The message to be written
	 */
	public static void writeToLogFileWithDateAndTime(String msg) { writeToLogFile(getDateTimeString() + " : "  + msg); }
	
	/**
	 * Writes the given string to the log file
	 * @param msg The message to be written
	 */
	public static void writeToLogFile(String msg) {
		if(logWriter != null) {
			try {
				logWriter.write(msg);
				
				logWriter.newLine();
				logWriter.flush();
			} catch(Exception e) {
				System.out.println("Failed to log message \"" + msg + "\" to log file: ");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Writes the given string to stdout and the log file
	 * @param msg The message to be written
	 */
	public static void log(String msg) {
		System.out.println(msg);
		writeToLogFileWithDateAndTime(msg);
	}
	
	/**
	 * Gets the name of some class, and optionally eliminates some prefix
	 * @param c The class
	 * @param prefixEliminate The prefix to eliminate, or {@code null} if none
	 * @return The name
	 */
	public static String getName(Class<?> c, String prefixEliminate) {
		String name = c.getClass().getName();
		name = name.substring(name.lastIndexOf('.') + 1);
		if(prefixEliminate != null && name.startsWith(prefixEliminate)) name = name.substring(prefixEliminate.length());
		
		return name;
	}
	
	/**
	 * Executes a runnable, with or without waiting for it to finish
	 * @param run The runnable
	 * @param async If true, it will be run asynchronously
	 */
	public static void exec(Runnable run, boolean async) {
		if(async) {
			Thread th = new Thread(run, "Async. run thread: " + run.hashCode());
			th.setDaemon(true);
			th.start();
		} else run.run();
	}
	
	/**
	 * Gets the name of a {@link Command}
	 * @param cmd The {@link Command}
	 * @return The name
	 */
	public static String getName(Command cmd) { return (cmd == null ? "(none)" : cmd.getName()); }
}