package micobyte.frc.lib.subsystem;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A {@link Subsystem} to connect to something via a Serial port
 */
public abstract class SubsystemSerial extends Subsystem {
	/** The {@link SerialPort port} */
	protected SerialPort connection;
	
	/**
	 * The current input line from the port<br>
	 * <b>This is an internal field. It should not be used by the end user.</b>
	 */
	protected String serialLine = "";
	
	/**
	 * Creates the subsystem, assuming 8N1, no flow control
	 * @param name The name of the subsystem
	 * @param baud The baud rate
	 * @param usbPort The USB serial port to use (0 - 2)
	 */
	public SubsystemSerial(String name, int baud, int usbPort) {
		this(name, baud, (usbPort == 0 ? Port.kUSB : (usbPort == 1 ? Port.kUSB1 : (usbPort == 2 ? Port.kUSB2 : null))));
	}
	
	/**
	 * Creates the subsystem, assuming 8N1, no flow control
	 * @param name The name of the subsystem
	 * @param baud The baud rate
	 * @param port The port to use
	 */
	public SubsystemSerial(String name, int baud, Port port) {
		this(name, baud, port, 8, Parity.kNone, StopBits.kOne, FlowControl.kNone);
	}
	
	/**
	 * Creates the subsystem
	 * @param name The name of the subsystem
	 * @param baud The baud rate
	 * @param port The port to use
	 * @param dataBits How many data bits
	 * @param par What parity
	 * @param stop How many stop bits
	 * @param flow What flow control
	 */
	public SubsystemSerial(String name, int baud, Port port, int dataBits, Parity par, StopBits stop, FlowControl flow) {
		super(name);
		
		connection = new SerialPort(baud, port, dataBits, par, stop);
		connection.setFlowControl(flow);
	}
	
	public void periodic() {
		while(connection.getBytesReceived() > 0) {
			char ch = (char)connection.read(1)[0];
			
			if(ch == 10 || ch == 13) {
				if(serialLine.length() > 0) onLineRecieved(serialLine);
				
				serialLine = "";
			} else serialLine += ch;
		}
	}
	
	/**
	 * Called when a line of serial data is recieved
	 * @param line The line
	 */
	public abstract void onLineRecieved(String line);
	
	/**
	 * Sends the given string
	 * @param toSend The string
	 */
	public void send(String toSend) {
		connection.writeString(toSend + "\n");
		connection.flush();
	}
}