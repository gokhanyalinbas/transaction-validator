package transactionvalidator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import transactionvalidator.fileadaptor.FileAdaptor;
import transactionvalidator.repository.TransactionRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TransactionServiceImplTest {
    private TransactionServiceImpl transactionService;
    @Mock
    private FileAdaptor fileAdaptor;
    @Mock
    private ReportService reportService;
    private TransactionValidator transactionValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionValidator = new TransactionValidatorImpl(new TransactionRepositoryImpl());
        transactionService = new TransactionServiceImpl(fileAdaptor, reportService, transactionValidator, new ArrayList<>());
    }

    @Test
    void processTransactions() throws IOException {
        transactionService.processTransactions();
        verify(fileAdaptor, times(1)).readFile();
        verify(reportService, times(1)).generateReport(any(ArrayList.class));
    }
}