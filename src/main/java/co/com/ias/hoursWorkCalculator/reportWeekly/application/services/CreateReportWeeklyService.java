package co.com.ias.hoursWorkCalculator.reportWeekly.application.services;


import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.errors.InputDataError;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;
import io.vavr.control.Validation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.text.ParseException;

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
        if(validation.isInvalid()) {
            throw validation.getError();
        }
        System.out.println(reportWeekly.getTechnicianIdentity());
        TechnicianIdentityNumber technicianIdentityNumber = reportWeekly.getTechnicianIdentity();

        System.out.println(technicianIdentityNumber);

        Optional<ServiceReport> reportWeeklyById = reportWeeklyRepository.getReportById(reportWeekly);
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

        double auxHourNormal, auxExtraHourNormal;
        double auxHourNocturnal, auxExtraNocturnal;
        double auxHourSunday, auxExtraSunday;

        NonEmptyString normalHourNonEmptyString = new NonEmptyString("0");
        NonEmptyString extraNormalHourNonEmptyString = new NonEmptyString("0");
        NonEmptyString nocturnalHourNonEmptyString = new NonEmptyString("0");
        NonEmptyString extraNocturnalNonEmptyString = new NonEmptyString("0");
        NonEmptyString sundayHourNonEmptyString = new NonEmptyString("0");
        NonEmptyString extraSundayHourNonEmptyString = new NonEmptyString("0");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dataFormat = null;
        try {
            dataFormat = format.parse(dateInit);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dataFormat);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);

        if(weekDay==7){
            auxHourSunday=hourFinishInt-hourInitInt;
            auxHourSunday=  auxHourSunday*0.01;

            if(auxHourSunday>480){
                auxExtraSunday=auxHourSunday-480;
                auxExtraSunday=  auxExtraSunday*0.01;
                extraSundayHourNonEmptyString=new NonEmptyString(String.valueOf(auxExtraSunday).replaceAll("0"," ").trim().replace(" ","0"));

            }
            sundayHourNonEmptyString= new NonEmptyString(String.valueOf(auxHourSunday).replaceAll("0"," ").trim().replace(" ","0"));
            System.out.println(sundayHourNonEmptyString);


        }else
        if(hourInitInt>=600&&hourInitInt<=1900){

            auxHourNormal= hourFinishInt-hourInitInt;
            auxHourNormal= auxHourNormal*0.01;
            if(auxHourNormal>480){
                auxExtraHourNormal=auxHourNormal-480;
                auxExtraHourNormal=  auxExtraHourNormal*0.01;
                extraNormalHourNonEmptyString=new NonEmptyString(String.valueOf(auxExtraHourNormal));

                extraNocturnalNonEmptyString.getValue().replaceAll("0"," ").trim().replace(" ","0");

            }

            normalHourNonEmptyString= new NonEmptyString(String.valueOf(auxHourNormal).replaceAll("0"," ").trim().replace(" ","0"));
            System.out.println(normalHourNonEmptyString);
           }else

        if(hourInitInt>=1900 || hourInitInt>=0000 && hourFinishInt <0600){

            auxHourNocturnal=hourFinishInt-hourInitInt;
            auxHourNocturnal= auxHourNocturnal*0.01;
            if(auxHourNocturnal>480){
                auxExtraNocturnal=auxHourNocturnal-480;
                auxExtraNocturnal=  auxExtraNocturnal*0.01;
                extraNocturnalNonEmptyString=new NonEmptyString(String.valueOf(auxExtraNocturnal).replaceAll("0"," ").trim().replace(" ","0"));

            }
            nocturnalHourNonEmptyString= new NonEmptyString(String.valueOf(auxHourNocturnal).replaceAll("0"," ").trim().replace(" ","0"));
            System.out.println(nocturnalHourNonEmptyString);
        }


        //reportWeeklyRepository.remove(reportWeekly);

        ReportWeekly reportWeeklyDone = new ReportWeekly(reportWeekly.getTechnicianIdentity(),normalHourNonEmptyString,nocturnalHourNonEmptyString,sundayHourNonEmptyString,extraNormalHourNonEmptyString,extraNocturnalNonEmptyString,extraSundayHourNonEmptyString,reportWeekly.getNumWeek());
        System.out.println(reportWeeklyDone);
        reportWeeklyRepository.storeReportWeekly(reportWeeklyDone);
        return new CreateReportWeeklyResponse(reportWeeklyDone);
    }
}


