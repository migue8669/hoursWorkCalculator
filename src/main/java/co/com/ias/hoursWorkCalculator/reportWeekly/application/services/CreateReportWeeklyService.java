package co.com.ias.hoursWorkCalculator.reportWeekly.application.services;


import co.com.ias.hoursWorkCalculator.commons.IdentificationNumber;
import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.errors.InputDataError;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.errors.ReportWeeklyAlreadyExistError;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;
import co.com.ias.hoursWorkCalculator.reportWeekly.infraestructure.adapters.out.SqlReportWeeklyRepository;
import io.vavr.control.Validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreateReportWeeklyService implements CreateReportWeeklyUseCase {

    private final ReportWeeklyRepository reportWeeklyRepository;
    private final ReportRepository reportRepository;
    public CreateReportWeeklyService(ReportWeeklyRepository reportWeeklyRepository, ReportRepository reportRepository) {
        this.reportWeeklyRepository = reportWeeklyRepository;
        this.reportRepository = reportRepository;
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

        final ReportWeekly reportWeekly = validation.get();
          final ServiceReport serviceReport;
        NonEmptyString technicianIdentityNumber = reportWeekly.getTechnicianIdentity();
        Optional<ReportWeekly> reportWeeklyById = reportWeeklyRepository.getReportWeeklyById(technicianIdentityNumber);


        Optional<ServiceReport> reportById = reportWeeklyRepository.getReportById(technicianIdentityNumber);
        Collection<ServiceReport> list = reportWeeklyRepository.listReports().stream().collect(Collectors.toList());


        if(validation.isInvalid()) {
            throw validation.getError();
        }


        System.out.println("CreateReportWeeklyService");
        System.out.println(reportById);

        System.out.println("list "+list
        );

        if (reportWeeklyById.isPresent()) {
            throw new ReportWeeklyAlreadyExistError(technicianIdentityNumber);
        }

        reportWeeklyRepository.storeReportWeekly(reportWeekly);

        return new CreateReportWeeklyResponse(reportWeekly);
    }    }

