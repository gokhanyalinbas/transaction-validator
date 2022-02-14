package transactionvalidator.fileadaptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transactionvalidator.exception.FileWriterException;
import transactionvalidator.model.OutputModel;
import transactionvalidator.model.RecordModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVFileTest {

    private CSVFile csvFile;

    @BeforeEach
    void setUp() {
        csvFile = new CSVFile();
    }

    @Test
    void readFile() throws IOException {
        List<RecordModel> recordModelList = csvFile.readFile("records.csv");
        assertEquals(10, recordModelList.size());
        assertEquals(194261, recordModelList.get(0).getReference());
    }

    @Test
    void writeFile() throws IOException {
        List<OutputModel> outputModels = new ArrayList<>();
        OutputModel model = OutputModel.builder()
                .reference(12345)
                .description("desc")
                .build();
        outputModels.add(model);
        csvFile.writeFile(outputModels);
        // If no exception, test is ok
    }

    @Test
    void writeFileException() {
        List<OutputModel> outputModels = null;
        assertThrows(FileWriterException.class, () -> csvFile.writeFile(outputModels));

    }
}