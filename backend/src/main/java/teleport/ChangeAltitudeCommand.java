package teleport;

class ChangeAltitudeCommand extends PcmdCommand {
    public ChangeAltitudeCommand(int commandSeqNo, float gaz) {
        super(commandSeqNo, 0, 0, gaz, 0);
    }
}
