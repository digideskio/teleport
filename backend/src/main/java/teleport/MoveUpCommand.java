package teleport;

public class MoveUpCommand extends PCMDCommand {
	
	@Override
	public byte[] asBytes(int commandSequenceNumber) {
		return wrap(POINT_2, commandSequenceNumber).getBytes();
	}

	@Override
	protected String wrap(String command, int commandSequenceNumber) {
		return "AT*PCMD=" + commandSequenceNumber + ",1,0,0," + command + ",0";
	}
}
