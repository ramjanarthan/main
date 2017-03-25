package guitests;

import org.junit.Test;

import seedu.doist.logic.commands.AliasCommand;
import seedu.doist.logic.parser.AliasCommandParser;

public class AliasCommandTest extends DoistGUITest {

    @Test
    public void testInputAliasSuccess() {
        commandBox.runCommand("alias addeaais \\for add");
        assertResultMessage(String.format(AliasCommand.MESSAGE_SUCCESS, "addeaais", "add"));
    }

    @Test
    public void testInputAliasIsDefaultCommandWord() {
        commandBox.runCommand("alias add \\for edit");
        assertResultMessage(String.format(AliasCommand.MESSAGE_ALIAS_IS_DEFAULT_COMMAND_WORD, "add"));
    }

    @Test
    public void testCommandWordNotSpecified() {
        commandBox.runCommand("alias a");
        assertResultMessage(String.format(AliasCommandParser.MESSAGE_COMMAND_WORD_NOT_SPECIFIED,
                AliasCommand.MESSAGE_USAGE));
    }

    @Test
    public void testInputAliasFormatInvalid() {
        commandBox.runCommand("alias add a task \\for add");
        assertResultMessage(String.format(AliasCommandParser.MESSAGE_ALIAS_FORMAT_INVALID,
                AliasCommand.MESSAGE_USAGE));
    }

    @Test
    public void testDefaultCommandWordNotExist() {
        commandBox.runCommand("alias newAlias \\for notExistCommand");
        assertResultMessage(String.format(AliasCommand.MESSAGE_COMMAND_WORD_NOT_EXIST, "notExistCommand"));
    }
}
