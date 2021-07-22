package co.com.ias.hoursWorkCalculator.report.application.services;

import co.com.ias.hoursWorkCalculator.commons.ReportIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.errors.InputDataError;
import co.com.ias.hoursWorkCalculator.report.application.errors.ReportAlreadyExistsError;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportResponse;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.CreateReportUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import io.vavr.control.Validation;

import java.util.Optional;

public class CreateReportService implements CreateReportUseCase {
    private final ReportRepository repository;

    public CreateReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public CreateReportResponse execute(CreateReportRequest request) {
        Validation<InputDataError, ServiceReport> validation = ServiceReport.parseReport(
                request.getReportIdentityNumber(),

                request.getTechnicianIdentity(),
                request.getHourInit(),
                request.getDateInit(),
                request.getHourFinish(),
                request.getDateFinish(),
        request.getNumWeek());



        if(validation.isInvalid()) {
            throw validation.getError();
        }

        final ServiceReport serviceReport = validation.get();
        System.out.println(serviceReport.getReportIdentityNumber());
        ReportIdentityNumber reportIdentityNumber = serviceReport.getReportIdentityNumber();
        Optional<ServiceReport> reportById = repository.getReportById(reportIdentityNumber);
        if (reportById.isPresent()) {
            throw new ReportAlreadyExistsError(reportIdentityNumber);
        }
        repository.storeReport(serviceReport);

        return new CreateReportResponse(serviceReport);
    }
}
