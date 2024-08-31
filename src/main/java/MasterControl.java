import java.util.ArrayList;
import java.util.List;

public class MasterControl {
    private final CommandValidator commandValidator;
    private final CommandProcessor commandProcessor;
    private final CommandStorage commandStorage;

    public MasterControl(CommandValidator commandValidator,
                         CommandProcessor commandProcessor, CommandStorage commandStorage) {
        this.commandValidator = commandValidator;
        this.commandProcessor = commandProcessor;
        this.commandStorage = commandStorage;
    }

    public ArrayList<String> start(List<String> input) {
        List<String> copyInput = new ArrayList<>(input);
        for (String command : copyInput) {
            if (commandValidator.validate(command)) {
                commandProcessor.process(command);
            } else {
                commandStorage.addInvalidCommand(command);
            }
        }
        return getOutput();
    }

    private ArrayList getOutput() {
        return commandStorage.getOutput();
    }
}

