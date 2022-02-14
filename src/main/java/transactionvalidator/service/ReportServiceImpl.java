package transactionvalidator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transactionvalidator.fileadaptor.FileAdaptor;
import transactionvalidator.model.OutputModel;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final FileAdaptor fileAdaptor;

    @Override
    public void generateReport(List<OutputModel> outputModelList) throws IOException {
        fileAdaptor.writeFile(outputModelList);
    }
}
