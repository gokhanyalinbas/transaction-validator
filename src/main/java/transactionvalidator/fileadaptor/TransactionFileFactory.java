package transactionvalidator.fileadaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import transactionvalidator.constant.ApplicationConstant;
import transactionvalidator.exception.UnSupportedFileException;

@Component
@RequiredArgsConstructor
public class TransactionFileFactory {

    private final JsonTransactionFileImpl jsonTransactionFile;
    private final CsvTransactionFileImpl csvTransactionFile;

    public TransactionFile getFile(String fileType) {
        switch (fileType.toUpperCase()) {
            case ApplicationConstant.CSV:
                return csvTransactionFile;
            case ApplicationConstant.JSON:
                return jsonTransactionFile;
            default:
                throw new UnSupportedFileException("Unsupported file type ! " + fileType);
        }
    }
}
