package co.com.ias.hoursWorkCalculator.reportWeekly.application.services;


import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
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
        System.out.println(request.getHour());

        System.out.println(request.getNightHour());
        System.out.println(request.getSundayHour());

        System.out.println(request.getExtraHour());

        System.out.println(request.getExtraNightHour());

        System.out.println(request.getExtraSundayHour());
        System.out.println(request.getNumWeek());
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

        final ReportWeekly reportWeekly = validation.get();
        System.out.println(reportWeekly.getTechnicianIdentity());
        TechnicianIdentityNumber technicianIdentityNumber = reportWeekly.getTechnicianIdentity();

        System.out.println(technicianIdentityNumber);
        Calculator calculator = new Calculator();

        Optional<ServiceReport> reportWeeklyById = reportWeeklyRepository.getReportById(reportWeekly.getTechnicianIdentity());
        System.out.println(reportWeeklyById.get().getTechnicianIdentity());
        Optional<ServiceReport> serviceReport = reportRepository.getReportByIdTechnician(technicianIdentityNumber);

        if(validation.isInvalid()) {
            throw validation.getError();
        }


        String reportIdentityNumber=serviceReport.get().getReportIdentityNumber().getValue().replaceAll("[^-?0-9]+", "");
        String technicianIdentity=serviceReport.get().getTechnicianIdentity().getValue().toString().replaceAll("[^-?0-9]+", "");
        String hourInit=serviceReport.get().getHourInit().getValue().toString().replaceAll("[^-?0-9]+", "");
        String dateInit=serviceReport.get().getDateInit().getValue().toString().replaceAll("[^-?0-9]+", "");
        String hourFinish=serviceReport.get().getHourFinish().getValue().toString().replaceAll("[^-?0-9]+", "");
        String dateFinish=serviceReport.get().getDateFinish().getValue().toString().replaceAll("[^-?0-9]+", "");
        String numWeek=serviceReport.get().getNumWeek().getValue().toString().replaceAll("[^-?0-9]+", "");

        System.out.println(reportIdentityNumber);
        System.out.println(technicianIdentity);
        System.out.println(hourInit);
        System.out.println(dateInit);
        System.out.println(hourFinish);
        System.out.println(dateFinish);
        System.out.println(numWeek);
        int hourInitInt= Integer.parseInt(hourInit);
        int hourFinishInt=Integer.parseInt(hourFinish);
        int auxHourNormal, auxExtraHourNormal;
        NonEmptyString normalHourNonEmptyString,auxNormalHourNonEmptyString;
        if(hourInitInt>600&&hourFinishInt<2000){
            auxHourNormal= hourFinishInt-hourInitInt;
            if(auxHourNormal>480){
                auxExtraHourNormal=auxHourNormal-480;
            }
            normalHourNonEmptyString= new NonEmptyString(String.valueOf(auxHourNormal));
            System.out.println(normalHourNonEmptyString);
        }

        if(hourInitInt>1900 && hourFinishInt<800){
            auxHourNormal=hourFinishInt-hourInitInt;
            normalHourNonEmptyString= new NonEmptyString(String.valueOf(auxHourNormal));
            System.out.println(normalHourNonEmptyString);
        }
        if(hourInitInt>1900 && hourFinishInt<800){
            auxHourNormal=hourFinishInt-hourInitInt;
            normalHourNonEmptyString= new NonEmptyString(String.valueOf(auxHourNormal));
            System.out.println(normalHourNonEmptyString);

        }


      //  if (reportWeeklyById.isPresent()) {
        //    throw new ReportWeeklyAlreadyExistError(technicianIdentityNumber);
        //}
        System.out.println("despues del if"+reportWeekly);
        reportWeeklyRepository.storeReportWeekly(reportWeekly);

        return new CreateReportWeeklyResponse(reportWeekly);

    }
}


