package transactionvalidator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transactionvalidator.constant.ApplicationConstant;
import transactionvalidator.fileadaptor.FileAdaptor;
import transactionvalidator.model.OutputModel;
import transactionvalidator.model.RecordModel;
import transactionvalidator.model.TransactionModel;
import transactionvalidator.repository.TransactionRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final FileAdaptor fileAdaptor;
    private final TransactionRepository transactionRepository;
    private final List<OutputModel> outputModelList = new ArrayList<>();

    @Override
    public void processTransactions() throws IOException {
        fileAdaptor.readFile().stream().forEach(record -> validate(record));
        if (outputModelList.size() > 0) {
            fileAdaptor.writeFile(outputModelList);
        }

    }

    private void validate(final RecordModel record) {

        TransactionModel transactionModel = new TransactionModel(record);
        boolean isBalanceCorrect = transactionModel.isTransactionValid();
        boolean isUnique = transactionRepository.add(transactionModel.getRecordModel().getReference());

        if (!isUnique || !isBalanceCorrect) {
            StringBuilder stringBuilder = new StringBuilder();
            if (!isUnique)
                stringBuilder.append(ApplicationConstant.INVALID_TRANSACTION_REFERENCE);
            if (!isBalanceCorrect)
                stringBuilder.append(ApplicationConstant.INVALID_BALANCE);
            addOutputList(transactionModel, stringBuilder);
        }


    }

    private void addOutputList(TransactionModel transactionModel, StringBuilder stringBuilder) {
        OutputModel model = OutputModel.builder()
                .reference(transactionModel.getRecordModel().getReference())
                .description(stringBuilder.toString())
                .build();
        outputModelList.add(model);
    }
}
