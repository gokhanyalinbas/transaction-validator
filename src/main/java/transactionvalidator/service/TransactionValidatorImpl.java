package transactionvalidator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transactionvalidator.constant.ApplicationConstant;
import transactionvalidator.model.OutputModel;
import transactionvalidator.model.TransactionModel;
import transactionvalidator.repository.TransactionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionValidatorImpl implements TransactionValidator {

    private final TransactionRepository transactionRepository;


    @Override
    public Optional<OutputModel> validate(TransactionModel transactionModel) {

        boolean isUnique = isUnique(transactionModel);
        boolean isBalanceCorrect = isBalanceCorrect(transactionModel);
        if (!isUnique || !isBalanceCorrect) {
            StringBuilder stringBuilder = new StringBuilder();
            if (!isUnique)
                stringBuilder.append(ApplicationConstant.INVALID_TRANSACTION_REFERENCE);
            if (!isBalanceCorrect)
                stringBuilder.append(ApplicationConstant.INVALID_BALANCE);

            Optional<OutputModel> model = Optional.of(OutputModel.builder()
                    .description(stringBuilder.toString())
                    .reference(transactionModel.getRecordModel().getReference())
                    .build());
            return model;
        }
        return Optional.empty();
    }

    private boolean isUnique(TransactionModel transactionModel) {
        return transactionRepository.add(transactionModel.getRecordModel().getReference());
    }

    private boolean isBalanceCorrect(TransactionModel transactionModel) {
        return transactionModel.isTransactionValid();
    }


}
