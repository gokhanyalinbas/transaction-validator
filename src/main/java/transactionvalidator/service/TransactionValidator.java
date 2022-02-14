package transactionvalidator.service;

import transactionvalidator.model.OutputModel;
import transactionvalidator.model.TransactionModel;

import java.util.Optional;

public interface TransactionValidator {

    Optional<OutputModel> validate(TransactionModel transactionModel);
}
