package transactionvalidator.fileadaptor;

import transactionvalidator.model.OutputModel;
import transactionvalidator.model.RecordModel;

import java.io.IOException;
import java.util.List;

public interface TransactionFile {
    List<RecordModel> readFile(String fileName) throws IOException;

    void writeFile(List<OutputModel> outputModels) throws IOException;
}
