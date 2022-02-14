package transactionvalidator.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionRepositoryImplTest {

    @Test
    void add() {
        TransactionRepositoryImpl transactionRepository = new TransactionRepositoryImpl();
        assertEquals(true, transactionRepository.add(123));
        assertEquals(false, transactionRepository.add(123));
    }
}