package transactionvalidator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import transactionvalidator.service.TransactionService;

@SpringBootApplication
@RequiredArgsConstructor
public class TransactionValidatorApplication implements CommandLineRunner {
    private final TransactionService transactionService;

    public static void main(String[] args) {
        SpringApplication.run(TransactionValidatorApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        transactionService.processTransactions();
        System.exit(0);
    }
}
