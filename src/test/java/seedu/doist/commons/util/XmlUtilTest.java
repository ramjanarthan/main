package seedu.doist.commons.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.doist.model.TodoList;
import seedu.doist.storage.XmlSerializableTodoList;
import seedu.doist.testutil.TestUtil;
import seedu.doist.testutil.TodoListBuilder;

public class XmlUtilTest {

    private static final String TEST_DATA_FOLDER = FileUtil.getPath("src/test/data/XmlUtilTest/");
    private static final File EMPTY_FILE = new File(TEST_DATA_FOLDER + "empty.xml");
    private static final File MISSING_FILE = new File(TEST_DATA_FOLDER + "missing.xml");
    private static final File VALID_FILE = new File(TEST_DATA_FOLDER + "validTodoList.xml");
    private static final File TEMP_FILE = new File(TestUtil.getFilePathInSandboxFolder("tempTodoList.xml"));

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getDataFromFile_nullFile_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.getDataFromFile(null, TodoList.class);
    }

    @Test
    public void getDataFromFile_nullClass_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.getDataFromFile(VALID_FILE, null);
    }

    @Test
    public void getDataFromFile_missingFile_FileNotFoundException() throws Exception {
        thrown.expect(FileNotFoundException.class);
        XmlUtil.getDataFromFile(MISSING_FILE, TodoList.class);
    }

    @Test
    public void getDataFromFile_emptyFile_DataFormatMismatchException() throws Exception {
        thrown.expect(JAXBException.class);
        XmlUtil.getDataFromFile(EMPTY_FILE, TodoList.class);
    }

    @Test
    public void getDataFromFile_validFile_validResult() throws Exception {
        XmlSerializableTodoList dataFromFile = XmlUtil.getDataFromFile(VALID_FILE, XmlSerializableTodoList.class);
        assertEquals(7, dataFromFile.getTaskList().size());
        assertEquals(4, dataFromFile.getTagList().size());
    }

    @Test
    public void saveDataToFile_nullFile_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.saveDataToFile(null, new TodoList());
    }

    @Test
    public void saveDataToFile_nullClass_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.saveDataToFile(VALID_FILE, null);
    }

    @Test
    public void saveDataToFile_missingFile_FileNotFoundException() throws Exception {
        thrown.expect(FileNotFoundException.class);
        XmlUtil.saveDataToFile(MISSING_FILE, new TodoList());
    }

    @Test
    public void saveDataToFile_validFile_dataSaved() throws Exception {
        TEMP_FILE.createNewFile();
        XmlSerializableTodoList dataToWrite = new XmlSerializableTodoList(new TodoList());
        XmlUtil.saveDataToFile(TEMP_FILE, dataToWrite);
        XmlSerializableTodoList dataFromFile = XmlUtil.getDataFromFile(TEMP_FILE, XmlSerializableTodoList.class);
        assertEquals(new TodoList(dataToWrite), new TodoList(dataFromFile));

        TodoListBuilder builder = new TodoListBuilder(new TodoList());
        dataToWrite = new XmlSerializableTodoList(
                builder.withTask(TestUtil.generateSamplePersonData().get(0)).withTag("Friends").build());

        XmlUtil.saveDataToFile(TEMP_FILE, dataToWrite);
        dataFromFile = XmlUtil.getDataFromFile(TEMP_FILE, XmlSerializableTodoList.class);
        assertEquals(new TodoList(dataToWrite), new TodoList(dataFromFile));
    }
}
