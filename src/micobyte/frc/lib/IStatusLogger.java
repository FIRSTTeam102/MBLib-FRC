package micobyte.frc.lib;

/**
 * A {@link FunctionalInterface functional interface} that represents something that can log a status message
 */
public interface IStatusLogger {
	
	/**
	 * Logs the given status
	 * @param status The status
	 */
	public void logStatus(String status);
}