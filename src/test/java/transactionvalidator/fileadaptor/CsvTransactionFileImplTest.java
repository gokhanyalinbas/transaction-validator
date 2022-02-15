package transactionvalidator.fileadaptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transactionvalidator.model.OutputModel;
import transactionvalidator.model.RecordModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvTransactionFileImplTest {

    private CsvTransactionFileImpl csvTransactionFileImpl;

    @BeforeEach
    void setUp() {
        csvTransactionFileImpl = new CsvTransactionFileImpl();
    }

    @Test
    void readFile() throws IOException {
        List<RecordModel> recordModelList = csvTransactionFileImpl.readFile("records.csv");
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
        csvTransactionFileImpl.writeFile(outputModels);
        // If no exception, test is ok
    }


}