package transactionvalidator.repository;

import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private static final HashSet<Long> transactionRespository = new HashSet<>();

    @Override
    public boolean add(final long reference) {
        return transactionRespository.add(reference);
    }
}
