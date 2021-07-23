package co.com.ias.hoursWorkCalculator.report.application.services;

import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;

import co.com.ias.hoursWorkCalculator.report.application.errors.InputDataError;
import co.com.ias.hoursWorkCalculator.report.application.errors.ReportWeeklyAlreadyExistError;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportWeeklyResponse;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportWeeklyRepository;
import io.vavr.control.Validation;

import java.util.Collection;
import java.util.Optional;

public class CreateReportWeeklyService implements CreateReportWeeklyUseCase {

    private final ReportWeeklyRepository reportWeeklyRepository;
    private final ReportRepository reportRepository;


    public CreateReportWeeklyService(ReportWeeklyRepository reportWeeklyRepository, ReportRepository reportRepository) {
        this.reportWeeklyRepository = reportWeeklyRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public CreateReportWeeklyResponse execute(CreateReportWeeklyRequest request) {
        System.out.println("adentro execute");
        Validation<InputDataError, ReportWeekly> validation = ReportWeekly.WeeklyReport(
                request.getTechnicianIdentity(),

                request.getReportIdentityNumber(),
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

        final ReportWeekly reportWeekly = validation.get();

        TechnicianIdentityNumber technicianIdentityNumber = reportWeekly.getTechnicianIdentity();
        Optional<ReportWeekly> reportWeeklyById = reportWeeklyRepository.getReportWeeklyById(technicianIdentityNumber);
        System.out.println(reportWeeklyById);
        if (reportWeeklyById.isPresent()) {
            throw new ReportWeeklyAlreadyExistError(technicianIdentityNumber);
        }

        reportWeeklyRepository.storeReportWeekly(reportWeekly);

        return new CreateReportWeeklyResponse(reportWeekly);
    }

}