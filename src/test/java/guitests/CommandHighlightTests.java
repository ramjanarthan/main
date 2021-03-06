package guitests;

import org.junit.Test;

//@@author A0147980U
public class CommandHighlightTests extends DoistGUITest {
    @Test
    public void testCorrectHighlightWithCompleteCommands() {
        String input = "do a new task \\from today \\to tomorrow \\as important \\under work";
        assertCorrectHighlightAfterInput(input);
    }

    @Test
    public void testCorrectHighlightWithIncorrectTimeKey() {
        String input = "do a new task \\frm today \\too tomorrow \\as important \\under work";
        assertCorrectHighlightAfterInput(input);
    }

    @Test
    public void testCorrectHighlightWithEmptyCommand() {
        String input = "";
        assertCorrectHighlightAfterInput(input);
    }

    @Test
    public void testCorrectHighlightWithKeyEnd() {
        String input = "add a task \by";
        assertCorrectHighlightAfterInput(input);
    }

    private void assertCorrectHighlightAfterInput(String input) {
        commandBox.enterCommand(input);
        assertCorrectHighlight();
    }
}
