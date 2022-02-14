package transactionvalidator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import transactionvalidator.constant.ApplicationConstant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionModel {

    private RecordModel recordModel;

    public boolean isTransactionValid() {
        return recordModel.getEndBalance()
                .compareTo(recordModel.getStartBalance()
                        .add(recordModel.getMutation())) == ApplicationConstant.EQUAL;
    }
}
