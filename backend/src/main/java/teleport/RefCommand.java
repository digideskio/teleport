package teleport;

public abstract class RefCommand implements DroneCommand {
	
	protected byte[] asBytes(String command, int commandSequenceNumber) {
		return wrap(command, commandSequenceNumber).getBytes();
	}
	
	protected String wrap(String command, int commandSequenceNumber) {
		return "AT*REF=" + commandSequenceNumber +"," + command + "\r";
	}
	
	
	@Override
	public String name() {
		return getClass().getSimpleName();
	}
}
