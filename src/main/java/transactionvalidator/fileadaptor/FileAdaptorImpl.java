package transactionvalidator.fileadaptor;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import transactionvalidator.constant.ApplicationConstant;
import transactionvalidator.exception.UnSupportedFileException;
import transactionvalidator.model.OutputModel;
import transactionvalidator.model.RecordModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileAdaptorImpl implements FileAdaptor {
    private final JSONFile jsonFile;
    private final CSVFile csvFile;

    @Override
    public List<RecordModel> readFile() throws IOException {
        File file = new File(ApplicationConstant.INPUT_FILEPATH);
        String[] inputFiles = file.list();
        return getRecordsFromFiles(inputFiles).get();
    }

    private Optional<List<RecordModel>> getRecordsFromFiles(String[] inputFiles) throws IOException {
        List<RecordModel> recordModels = new ArrayList<>();
        for (String file : inputFiles) {
            recordModels.addAll(processFile(file));
        }
        return Optional.of(recordModels);
    }

    private List<RecordModel> processFile(String file) throws IOException {

        String fileType = getFileType(file);
        switch (fileType.toUpperCase()) {
            case ApplicationConstant.CSV:
                return csvFile.readFile(file);
            case ApplicationConstant.JSON:
                return jsonFile.readFile(file);
            default:
                throw new UnSupportedFileException("Unsupported file type ! " + fileType);
        }
    }

    private String getFileType(String file) {
        return FilenameUtils.getExtension(file);
    }

    @Override
    public void writeFile(List<OutputModel> outputModels) throws IOException {

        jsonFile.writeFile(outputModels);
        csvFile.writeFile(outputModels);
    }
}
