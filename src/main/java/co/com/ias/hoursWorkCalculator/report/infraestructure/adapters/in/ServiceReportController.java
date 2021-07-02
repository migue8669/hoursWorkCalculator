package co.com.ias.hoursWorkCalculator.report.infraestructure.adapters.in;

import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.ListReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.CreateReportUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.ListReportsUseCase;
import co.com.ias.hoursWorkCalculator.report.infraestructure.commons.UseCaseHttpExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/report")
public class ServiceReportController {
    private final UseCaseHttpExecutor useCaseHttpExecutor;
    private final CreateReportWeeklyUseCase createReportWeeklyUseCase;
    private final CreateReportUseCase createReportUseCase;
    private final ListReportsUseCase listReportsUseCase;
    @Autowired
    public ServiceReportController( UseCaseHttpExecutor useCaseHttpExecutor, CreateReportUseCase createReportUseCase, CreateReportWeeklyUseCase createREportWeeklyUseCase, ListReportsUseCase listReportsUseCase) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.createReportWeeklyUseCase = createREportWeeklyUseCase;
        this.listReportsUseCase = listReportsUseCase;
        this.createReportUseCase=createReportUseCase;
    }

    @GetMapping
    public ResponseEntity listReportsHandler(
            @RequestParam(name = "limit", defaultValue = "10") String limit,
            @RequestParam(name = "skip", defaultValue = "0") String skip
    ) {
        Integer limitInt = Integer.parseInt(limit, 10);
        Integer skipInt = Integer.parseInt(skip, 10);
        return useCaseHttpExecutor.executeUseCase(
                listReportsUseCase,
                new ListReportRequest(limitInt, skipInt)
        );
    }


    @PostMapping
    public ResponseEntity createReportHandler(
            @RequestBody CreateReportRequest request
    ) {
        return useCaseHttpExecutor.executeUseCase(
                createReportUseCase,
                request
        );
    }
    @RequestMapping(value="/new", method =RequestMethod.POST)
    public ResponseEntity createReportWeeklyHandler(
            @RequestBody CreateReportWeeklyRequest request
    ) {
        return useCaseHttpExecutor.executeUseCase(
                createReportWeeklyUseCase,
                request
        );
    }
}
