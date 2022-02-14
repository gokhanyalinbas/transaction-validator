package transactionvalidator.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ModelTest {

    @Test
    void outputModelTest() {
        OutputModel model = OutputModel.builder()
                .description("desc")
                .reference(12345)
                .build();
        assertEquals("desc", model.getDescription());
        assertEquals(12345, model.getReference());

        OutputModel modelWithNoArgs = new OutputModel();
        assertEquals(0, modelWithNoArgs.getReference());
        assertNull(modelWithNoArgs.getDescription());
    }

    @Test
    void recordModelTest() {
        RecordModel model = getModel();

        assertEquals("desc", model.getDescription());
        assertEquals(12345, model.getReference());
        assertEquals("12345", model.getAccountNumber());
        assertEquals(12.50, model.getEndBalance().doubleValue());
        assertEquals(12.1, model.getMutation().doubleValue());
        assertEquals(45.78, model.getStartBalance().doubleValue());
    }

    @Test
    void transactionModelTest() {
        TransactionModel transactionModel = getTransaction();
        assertEquals("desc", transactionModel.getRecordModel().getDescription());
        assertEquals(12345, transactionModel.getRecordModel().getReference());
        assertEquals("12345", transactionModel.getRecordModel().getAccountNumber());
        assertEquals(12.50, transactionModel.getRecordModel().getEndBalance().doubleValue());
        assertEquals(12.1, transactionModel.getRecordModel().getMutation().doubleValue());
        assertEquals(45.78, transactionModel.getRecordModel().getStartBalance().doubleValue());

        TransactionModel transactionModelwithnoArgs = new TransactionModel();
        assertNull(transactionModelwithnoArgs.getRecordModel());
    }

    private TransactionModel getTransaction() {
        return TransactionModel.builder()
                .recordModel(getModel())
                .build();
    }

    @Test
    void invalidTransactionTest() {

        TransactionModel transactionModel = getTransaction();
        assertEquals(false, transactionModel.isTransactionValid());
    }

    @Test
    void validTransactionTest() {

        TransactionModel transactionModel = TransactionModel.builder()
                .recordModel(getValidModel())
                .build();
        assertEquals(true, transactionModel.isTransactionValid());
    }

    private RecordModel getModel() {
        return RecordModel.builder()
                .description("desc")
                .reference(12345)
                .accountNumber("12345")
                .endBalance(BigDecimal.valueOf(12.50))
                .mutation(BigDecimal.valueOf(12.1))
                .startBalance(BigDecimal.valueOf(45.78))
                .build();
    }

    private RecordModel getValidModel() {
        return RecordModel.builder()
                .description("desc")
                .reference(12345)
                .accountNumber("12345")
                .endBalance(BigDecimal.valueOf(57.88))
                .mutation(BigDecimal.valueOf(12.1))
                .startBalance(BigDecimal.valueOf(45.78))
                .build();
    }
}