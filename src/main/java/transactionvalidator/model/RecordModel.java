package transactionvalidator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordModel {

    private long reference;
    private String accountNumber;
    private BigDecimal startBalance;
    private BigDecimal mutation;
    private BigDecimal endBalance;
    private String description;
}
