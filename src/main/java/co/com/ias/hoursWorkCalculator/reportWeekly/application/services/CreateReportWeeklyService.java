package co.com.ias.hoursWorkCalculator.reportWeekly.application.services;


import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.Calculator;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.errors.InputDataError;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;
import io.vavr.control.Validation;

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
        System.out.println("request");
        System.out.println(request.getTechnicianIdentity());
        Validation<InputDataError, ReportWeekly> validation = ReportWeekly.WeeklyReport(

                request.getTechnicianIdentity(),

          request.getNumWeek()

        );

        final ReportWeekly reportWeekly = validation.get();
        System.out.println(reportWeekly.getTechnicianIdentity());
        TechnicianIdentityNumber technicianIdentityNumber = reportWeekly.getTechnicianIdentity();

        System.out.println(technicianIdentityNumber);
        Calculator calculator = new Calculator();

        Optional<ReportWeekly> reportWeeklyById = reportWeeklyRepository.getReportById(reportWeekly.getTechnicianIdentity());
        System.out.println(reportWeeklyById.get().getTechnicianIdentity());
        Optional<ServiceReport> serviceReport = reportRepository.getReportByIdTechnician(technicianIdentityNumber);




        if(validation.isInvalid()) {
            throw validation.getError();
        }




      //  if (reportWeeklyById.isPresent()) {
        //    throw new ReportWeeklyAlreadyExistError(technicianIdentityNumber);
        //}
        System.out.println("despues del if"+reportWeekly);
        reportWeeklyRepository.storeReportWeekly(reportWeekly);

        return new CreateReportWeeklyResponse(reportWeekly);

    }
}


