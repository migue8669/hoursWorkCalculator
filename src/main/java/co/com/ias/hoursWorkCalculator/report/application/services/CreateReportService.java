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
        final ServiceReport serviceReport = validation.get();
        technicianIdentity=serviceReport.getTechnicianIdentity();
        dateInit=serviceReport.getDateInit();
        dateFinish=serviceReport.getDateFinish();
        String hourInitString=serviceReport.getHourInit().toString().replaceAll("[^-?0-9]+", "");
        String hourFinishString=serviceReport.getHourFinish().toString().replaceAll("[^-?0-9]+", "");
        int normalHour=0,nocturnalHour=0,sundayHour=0;
        String normalHourString="",nocturnalHourString="",sundayHourString="";
        int hourInitIntAux= Integer.parseInt(hourInitString);
        int hourFinishIntAux=Integer.parseInt(hourFinishString);
        String dateInitAux =  serviceReport.getDateInit().toString().replaceAll("[^-?0-9]+", "");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dataFormat = format.parse(dateInitAux);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dataFormat);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);


        if(hourFinishIntAux>6 && hourFinishIntAux<9){
            normalHour=hourFinishIntAux-hourInitIntAux;
            normalHourString=String.valueOf(normalHour);
        }
        if(hourFinishIntAux>19 && hourFinishIntAux<8){
            nocturnalHour=hourFinishIntAux-hourInitIntAux;
            nocturnalHourString=String.valueOf(nocturnalHour);
        }

        if(weekDay==7){
            sundayHour=hourFinishIntAux-hourInitIntAux;
            sundayHourString=String.valueOf(sundayHour);
            }
      //  ReportWeekly reportWeekly=new ReportWeekly(technicianIdentity, normalHourString);

        ReportIdentityNumber reportIdentityNumber = serviceReport.getReportIdentityNumber();
        Optional<ServiceReport> reportById = repository.getReportById(reportIdentityNumber);

        if (reportById.isPresent()) {
            throw new ReportAlreadyExistsError(reportIdentityNumber);
        }
        repository.storeReport(serviceReport);

        return new CreateReportResponse(serviceReport);
    }
}
