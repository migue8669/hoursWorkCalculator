package co.com.ias.hoursWorkCalculator.reportWeekly.infraestructure.adapters.in;


import co.com.ias.hoursWorkCalculator.report.application.model.ListReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.ListReportsUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.ListWeeklyReportRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.ListReportWeeklyUseCase;

import co.com.ias.hoursWorkCalculator.commons.UseCaseHttpExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/reportWeekly", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportWeeklyController {
    private final UseCaseHttpExecutor useCaseHttpExecutor;
    private final CreateReportWeeklyUseCase createReportWeeklyUseCase;
    private final ListReportWeeklyUseCase listReportWeeklyUseCase;
    private final ListReportsUseCase listReportUseCase;

    @Autowired
    public ReportWeeklyController(UseCaseHttpExecutor useCaseHttpExecutor, CreateReportWeeklyUseCase createREportWeeklyUseCase, ListReportWeeklyUseCase listReportWeeklyUseCase, ListReportsUseCase listReportUseCase) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.createReportWeeklyUseCase = createREportWeeklyUseCase;
        this.listReportWeeklyUseCase = listReportWeeklyUseCase;

        this.listReportUseCase = listReportUseCase;
    }

    @GetMapping
    public ResponseEntity listReportsHandler(
            @RequestParam(name = "limit", defaultValue = "10") String limit,
            @RequestParam(name = "skip", defaultValue = "0") String skip
    ) {
        Integer limitInt = Integer.parseInt(limit, 10);
        Integer skipInt = Integer.parseInt(skip, 10);
        return useCaseHttpExecutor.executeUseCase(
                listReportWeeklyUseCase,
                new ListWeeklyReportRequest(limitInt, skipInt)
        );
    }


    @PostMapping
    public ResponseEntity createReportWeeklyHandler(
            @RequestBody CreateReportWeeklyRequest request
    ) {
        return useCaseHttpExecutor.executeUseCase(
                createReportWeeklyUseCase,
                request
        );
    }


    }

