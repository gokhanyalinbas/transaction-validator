package transactionvalidator.service;

import transactionvalidator.model.OutputModel;

import java.io.IOException;
import java.util.List;

public interface ReportService {

    void generateReport(List<OutputModel> outputModelList) throws IOException;
}
