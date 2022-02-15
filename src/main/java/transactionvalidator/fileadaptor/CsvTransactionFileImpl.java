package transactionvalidator.fileadaptor;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.stereotype.Service;
import transactionvalidator.constant.ApplicationConstant;
import transactionvalidator.exception.FileWriterException;
import transactionvalidator.model.OutputModel;
import transactionvalidator.model.RecordModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvTransactionFileImpl implements TransactionFile {
    @Override
    public List<RecordModel> readFile(String fileName) throws IOException {
        CSVReader csvReader = new CSVReaderBuilder(new FileReader(ApplicationConstant.INPUT_FILEPATH + fileName))
                .withSkipLines(1)// Skip the  first line - header
                .build();
        return csvReader.readAll().stream().map(data -> getModel(data)).collect(Collectors.toList());
    }

    private RecordModel getModel(final String[] data) {
        return RecordModel.builder()
                .reference(Long.parseLong(data[0]))
                .accountNumber(data[1])
                .description(data[2])
                .startBalance(BigDecimal.valueOf(Double.parseDouble(data[3])))
                .mutation(BigDecimal.valueOf(Double.parseDouble(data[4])))
                .endBalance(BigDecimal.valueOf(Double.parseDouble(data[5])))
                .build();
    }

    @Override
    public void writeFile(List<OutputModel> outputModels) throws IOException {
        FileWriter fileWriter = new FileWriter(ApplicationConstant.OUTPUT_FILEPATH + ApplicationConstant.CSVFILE_NAME);
        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(fileWriter)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();
        try {
            sbc.write(outputModels);
        } catch (Exception e) {
            throw new FileWriterException(e.getMessage());
        } finally {
            fileWriter.close();
        }

    }
}
