package co.com.ias.hoursWorkCalculator.reportWeekly.application.services;


import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.errors.InputDataError;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.errors.ReportWeeklyAlreadyExistError;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;
import io.vavr.control.Validation;

import java.util.Optional;

public class CreateReportWeeklyService implements CreateReportWeeklyUseCase {
    private final ReportWeeklyRepository reportWeeklyRepository;

    public CreateReportWeeklyService(ReportWeeklyRepository reportWeeklyRepository) {
        this.reportWeeklyRepository = reportWeeklyRepository;
    }

    @Override
    public CreateReportWeeklyResponse execute(CreateReportWeeklyRequest request) {
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
        System.out.println(request);
        System.out.println(validation);
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
    }    }
