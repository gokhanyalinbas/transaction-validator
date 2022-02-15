package transactionvalidator.fileadaptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import transactionvalidator.constant.ApplicationConstant;
import transactionvalidator.model.OutputModel;
import transactionvalidator.model.RecordModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class JsonTransactionFileImpl implements TransactionFile {
    @Override
    public List<RecordModel> readFile(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<RecordModel>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = new FileInputStream(ApplicationConstant.INPUT_FILEPATH + fileName);
        return mapper.readValue(inputStream, typeReference);
    }

    @Override
    public void writeFile(List<OutputModel> outputModels) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(ApplicationConstant.OUTPUT_FILEPATH + ApplicationConstant.JSONFILE_NAME);
        mapper.writeValue(file, outputModels);

    }
}
