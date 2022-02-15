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
        if (!isValid(isUnique, isBalanceCorrect)) {
            StringBuilder sb = new StringBuilder();
            getUniqueErrorDescription(isUnique).ifPresent(desc -> sb.append(desc));
            getBalanceErrorDescription(isBalanceCorrect).ifPresent(desc -> sb.append(desc));
            return getOutputModel(transactionModel, sb);
        }
        return Optional.empty();
    }

    private boolean isValid(boolean isUnique, boolean isBalanceCorrect) {
        return isUnique && isBalanceCorrect;
    }

    private boolean isUnique(final TransactionModel transactionModel) {
        return transactionRepository.add(transactionModel.getRecordModel().getReference());
    }

    private boolean isBalanceCorrect(final TransactionModel transactionModel) {
        return transactionModel.isTransactionValid();
    }

    private Optional<OutputModel> getOutputModel(TransactionModel transactionModel, StringBuilder stringBuilder) {
        Optional<OutputModel> model = Optional.of(OutputModel.builder()
                .description(stringBuilder.toString())
                .reference(transactionModel.getRecordModel().getReference())
                .build());
        return model;
    }

    private Optional<String> getUniqueErrorDescription(boolean flag) {
        return flag ? Optional.empty() : Optional.of(ApplicationConstant.INVALID_TRANSACTION_REFERENCE);
    }

    private Optional<String> getBalanceErrorDescription(boolean flag) {
        return flag ? Optional.empty() : Optional.of(ApplicationConstant.INVALID_BALANCE);
    }

}
