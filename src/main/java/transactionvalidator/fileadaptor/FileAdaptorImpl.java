package transactionvalidator.fileadaptor;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import transactionvalidator.constant.ApplicationConstant;
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
    private final TransactionFileFactory transactionFileFactory;

    @Override
    public List<RecordModel> readFile() throws IOException {
        File file = new File(ApplicationConstant.INPUT_FILEPATH);
        String[] inputFiles = file.list();
        return getRecordsFromFiles(inputFiles).get();
    }

    private Optional<List<RecordModel>> getRecordsFromFiles(final String[] inputFiles) throws IOException {
        List<RecordModel> recordModels = new ArrayList<>();
        for (String file : inputFiles) {
            recordModels.addAll(processFile(file));
        }
        return Optional.of(recordModels);
    }

    private List<RecordModel> processFile(final String file) throws IOException {

        String fileType = getFileType(file);
        return transactionFileFactory.getFile(fileType).readFile(file);
    }

    private String getFileType(final String file) {
        return FilenameUtils.getExtension(file);
    }

    @Override
    public void writeFile(final List<OutputModel> outputModels) throws IOException {
        transactionFileFactory.getFile(ApplicationConstant.CSV).writeFile(outputModels);
        transactionFileFactory.getFile(ApplicationConstant.JSON).writeFile(outputModels);
    }
}
