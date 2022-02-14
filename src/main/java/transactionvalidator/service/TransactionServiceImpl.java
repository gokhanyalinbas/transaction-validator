package transactionvalidator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transactionvalidator.fileadaptor.FileAdaptor;
import transactionvalidator.model.OutputModel;
import transactionvalidator.model.RecordModel;
import transactionvalidator.model.TransactionModel;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final FileAdaptor fileAdaptor;
    private final ReportService reportService;
    private final TransactionValidator transactionValidator;
    private final List<OutputModel> outputModelList;

    @Override
    public void processTransactions() throws IOException {
        fileAdaptor.readFile().stream().forEach(record -> validate(record));
        reportService.generateReport(outputModelList);


    }

    private void validate(RecordModel record) {
        transactionValidator.validate(new TransactionModel(record)).ifPresent(i -> outputModelList.add(i));
    }


}
