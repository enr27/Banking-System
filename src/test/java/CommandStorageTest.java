import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandStorageTest {

    private CommandStorage commandStorage;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandStorage = new CommandStorage(bank);
    }

    @Test
    public void command_storage_starts_empty() {
        assertEquals(0, commandStorage.getInvalidCommands().size());
    }

    @Test
    public void invalid_command_is_stored_in_list() {
        commandStorage.addInvalidCommand("creite idk 123 -5");
        assertEquals("creite idk 123 -5", commandStorage.getInvalidCommands().get(0));
    }

    @Test
    public void correct_size_of_list_after_doing_invalid_commands() {
        commandStorage.addInvalidCommand("create something 12345678 24");
        commandStorage.addInvalidCommand("deposit basketball 12345678");
        assertEquals(2, commandStorage.getInvalidCommands().size());
    }
}