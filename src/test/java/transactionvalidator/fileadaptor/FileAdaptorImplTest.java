package transactionvalidator.fileadaptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transactionvalidator.constant.ApplicationConstant;
import transactionvalidator.exception.UnSupportedFileException;
import transactionvalidator.model.RecordModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FileAdaptorImplTest {

    private FileAdaptorImpl fileAdaptor;

    @BeforeEach
    void setup() {
        JSONFile jsonFile = new JSONFile();
        CSVFile csvFile = new CSVFile();
        fileAdaptor = new FileAdaptorImpl(jsonFile, csvFile);
    }

    @Test
    void readFile() throws IOException {
        List<RecordModel> recordModelList = fileAdaptor.readFile();
        assertEquals(20, recordModelList.size());
    }

    @Test
    void readFileWithUnSupportedType() throws IOException {
        File file = new File(ApplicationConstant.INPUT_FILEPATH + "test.abc");
        file.createNewFile();
        assertThrows(UnSupportedFileException.class, () -> {
            fileAdaptor.readFile();
        });
        file.delete();
    }
}