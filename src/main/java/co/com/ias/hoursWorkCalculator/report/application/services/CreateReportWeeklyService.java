package co.com.ias.hoursWorkCalculator.report.application.services;


import co.com.ias.hoursWorkCalculator.report.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.errors.InputDataError;
import co.com.ias.hoursWorkCalculator.report.application.errors.ReportAlreadyExistsError;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportResponse;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportWeeklyResponse;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportWeeklyRepository;
import io.vavr.control.Validation;

import java.util.Optional;

public class CreateReportWeeklyService implements CreateReportWeeklyUseCase {
    private final ReportWeeklyRepository reportWeeklyRepository;

    public CreateReportWeeklyService(ReportWeeklyRepository reportWeeklyRepository) {
        this.reportWeeklyRepository = reportWeeklyRepository;
    }

    @Override
    public CreateReportWeeklyResponse execute(CreateReportWeeklyRequest request) {
        Validation<InputDataError, ReportWeekly> validation = ReportWeekly.parseReport(
                request.getTechnicianIdentity(),
                request.getReportdentityNumber(),
                request.getHour(),
                request.getNightHour(),
                request.getSundayHour(),
                request.getExtraHour(),
                request.getExtraNightHour(),
                request.getExtraSundayHour()

        );

        if(validation.isInvalid()) {
            throw validation.getError();
        }

        final ReportWeekly serviceReport = validation.get();

        ReportdentityNumber reportdentityNumber = serviceReport.getReportdentityNumber();
        Optional<ReportWeekly> studentById = reportWeeklyRepository.getReportWeeklyById(reportdentityNumber);

        if (studentById.isPresent()) {
            throw new ReportAlreadyExistsError(reportdentityNumber);
        }

        reportWeeklyRepository.storeReportWeekly(serviceReport);

        return new CreateReportWeeklyResponse(serviceReport);
    }    }

