package co.com.ias.hoursWorkCalculator.reportWeekly.application.services;

import co.com.ias.hoursWorkCalculator.commons.ReportIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.errors.ReportAlreadyExistsError;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportResponse;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.CreateReportUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.errors.InputDataError;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.DeleteReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.DeleteReportWeeklyResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.DeleteReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;
import io.vavr.control.Validation;

import java.util.Optional;

public class DeleteReportWeeklyServices implements DeleteReportWeeklyUseCase {
    private final ReportWeeklyRepository repository;

    public DeleteReportWeeklyServices(ReportWeeklyRepository repository) {
        this.repository = repository;
    }

    public DeleteReportWeeklyResponse execute(DeleteReportWeeklyRequest request) {
        Validation<InputDataError, ReportWeekly> validation = ReportWeekly.WeeklyReport(

                request.getTechnicianIdentity(),
                request.getHour(),
                request.getNightHour(),
                request.getSundayHour(),
                request.getExtraHour(),
                request.getExtraNightHour(),
                request.getExtraSundayHour(),
                request.getNumWeek()

        );


        if(validation.isInvalid()) {
            throw validation.getError();
        }

        final ReportWeekly reportWeekly = validation.get();
     //   System.out.println(reportWeekly.getReportIdentityNumber());
       // ReportIdentityNumber reportIdentityNumber = reportWeekly.getReportIdentityNumber();
       // Optional<ServiceReport> reportById = repository.getReportById(reportIdentityNumber);
     //   if (reportById.isPresent()) {
       //     throw new ReportAlreadyExistsError(reportIdentityNumber);
       // }
        repository.remove(reportWeekly);

        return new DeleteReportWeeklyResponse(reportWeekly);
    }


}