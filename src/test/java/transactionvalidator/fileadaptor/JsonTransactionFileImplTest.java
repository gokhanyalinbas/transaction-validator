package transactionvalidator.fileadaptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transactionvalidator.model.OutputModel;
import transactionvalidator.model.RecordModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonTransactionFileImplTest {

    private JsonTransactionFileImpl jsonTransactionFileImpl;

    @BeforeEach
    void setUp() {
        jsonTransactionFileImpl = new JsonTransactionFileImpl();
    }

    @Test
    void readFile() throws IOException {
        List<RecordModel> recordModels = jsonTransactionFileImpl.readFile("records.json");
        assertEquals(10, recordModels.size());
        assertEquals(130498, recordModels.get(0).getReference());
    }

    @Test
    void writeFile() throws IOException {
        List<OutputModel> outputModels = new ArrayList<>();
        OutputModel model = OutputModel.builder()
                .reference(12345)
                .description("desc")
                .build();
        outputModels.add(model);
        jsonTransactionFileImpl.writeFile(outputModels);
        // If no exception, test is ok
    }
}