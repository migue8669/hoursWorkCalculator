package co.com.ias.hoursWorkCalculator.report.application.services;

import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.domain.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.errors.InputDataError;
import co.com.ias.hoursWorkCalculator.report.application.errors.ReportAlreadyExistsError;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportResponse;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.CreateReportUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import io.vavr.control.Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateReportService implements CreateReportUseCase {
    private final ReportRepository repository;
    private TechnicianIdentityNumber technicianIdentity ;

    int hourInitAux;
    int hourFinishAux;
    NonEmptyString dateInit;
    NonEmptyString dateFinish;

    private ReportIdentityNumber reportIdentityNumber;

    public CreateReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public CreateReportResponse execute(CreateReportRequest request) throws ParseException {
        Validation<InputDataError, ServiceReport> validation = ServiceReport.parseReport(
                request.getTechnicianIdentity(),
                request.getReportIdentityNumber(),
                request.getHourInit(),
                request.getDateInit(),
                request.getHourFinish(),
                request.getDateFinish()


                );

        if(validation.isInvalid()) {
            throw validation.getError();
        }
        CreateReportWeeklyService createReportWeeklyService = null;
        final ServiceReport serviceReport = validation.get();
        String technicianIdentityString,reportIdentityNumberString;
        technicianIdentity=serviceReport.getTechnicianIdentity();
        reportIdentityNumber=serviceReport.getReportIdentityNumber();
        dateInit=serviceReport.getDateInit();
        dateFinish=serviceReport.getDateFinish();
        String hourInitString=serviceReport.getHourInit().toString().replaceAll("[^-?0-9]+", "");
        String hourFinishString=serviceReport.getHourFinish().toString().replaceAll("[^-?0-9]+", "");
        int normalHour=0,nocturnalHour=0,sundayHour=0;
        NonEmptyString normalHourString = null;
        NonEmptyString nocturnalHourString = null;
        NonEmptyString sundayHourString = null;
        String  hourString = null,nocturnalString = null, sundayString = null,cero="0";
        NonEmptyString noEmptyCero = null;
        int hourInitIntAux= Integer.parseInt(hourInitString);
        int hourFinishIntAux=Integer.parseInt(hourFinishString);
        String dateInitAux =  serviceReport.getDateInit().toString().replaceAll("[^-?0-9]+", "");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dataFormat = format.parse(dateInitAux);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dataFormat);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(hourFinishIntAux);
        if(hourFinishIntAux>600|| hourFinishIntAux<900){
            normalHour=hourFinishIntAux-hourInitIntAux;
            hourString=  String.valueOf(normalHour);
            normalHourString= new NonEmptyString(hourString);
            nocturnalHourString=new NonEmptyString(cero);
            sundayHourString=new NonEmptyString(cero);
           technicianIdentityString=technicianIdentity.toString();
           reportIdentityNumberString=reportIdentityNumber.toString();
     //       CreateReportWeeklyRequest createReportWeeklyRequest = new CreateReportWeeklyRequest(technicianIdentityString,reportIdentityNumberString, hourString,nocturnalString,sundayString,cero,cero,cero);
       //     createReportWeeklyService.execute(createReportWeeklyRequest);
            System.out.println("normalHour");

            System.out.println(normalHour);
        }else
        if(hourFinishIntAux>1900 || hourFinishIntAux<800){
            nocturnalHour=hourFinishIntAux-hourInitIntAux;
            nocturnalString=String.valueOf(nocturnalHour);
            nocturnalHourString=new NonEmptyString(nocturnalString);
            normalHourString=new NonEmptyString(cero);
            sundayHourString=new NonEmptyString(cero);
            technicianIdentityString=technicianIdentity.toString();
            reportIdentityNumberString=reportIdentityNumber.toString();
            CreateReportWeeklyRequest createReportWeeklyRequest = new CreateReportWeeklyRequest(technicianIdentityString,reportIdentityNumberString, hourString,nocturnalString,sundayString,cero,cero,cero);
            createReportWeeklyService.execute(createReportWeeklyRequest);
           // createReportWeeklyRequest.setHour(hourString);
          //  createReportWeeklyRequest.setNightHour(nocturnalString);
           // createReportWeeklyRequest.setSundayHour(sundayString);
            System.out.println(nocturnalHour);
            System.out.println("nocturnalHour");

        }else

        if(weekDay==7){

            sundayHour=hourFinishIntAux-hourInitIntAux;
            sundayString=String.valueOf(sundayHour);
            sundayHourString=new NonEmptyString(sundayString);
            nocturnalHourString=new NonEmptyString(cero);
            normalHourString=new NonEmptyString(cero);
            technicianIdentityString=technicianIdentity.toString();
            reportIdentityNumberString=reportIdentityNumber.toString();
            CreateReportWeeklyRequest createReportWeeklyRequest = new CreateReportWeeklyRequest(technicianIdentityString,reportIdentityNumberString, hourString,nocturnalString,sundayString,cero,cero,cero);
            createReportWeeklyService.execute(createReportWeeklyRequest);
        //    createReportWeeklyRequest.setHour(hourString);
         //   createReportWeeklyRequest.setNightHour(nocturnalString);
          //  createReportWeeklyRequest.setSundayHour(sundayString);
            System.out.println(sundayHourString);
            }
        System.out.println("a");
        noEmptyCero= new NonEmptyString(cero);
         //ReportWeekly reportWeekly=new ReportWeekly(technicianIdentity,reportIdentityNumber, normalHourString,nocturnalHourString,sundayHourString,noEmptyCero,noEmptyCero,noEmptyCero);
       // createReportWeeklyService.execute(createReportWeeklyRequest);






        ReportIdentityNumber reportIdentityNumber = serviceReport.getReportIdentityNumber();
        Optional<ServiceReport> reportById = repository.getReportById(reportIdentityNumber);

        if (reportById.isPresent()) {
            throw new ReportAlreadyExistsError(reportIdentityNumber);
        }
        repository.storeReport(serviceReport);

        return new CreateReportResponse(serviceReport);
    }
}
