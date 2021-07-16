package co.com.ias.hoursWorkCalculator.reportWeekly.application.domain;

import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Calculator {
ReportWeekly reportWeekly;
public  void calculate(ArrayList<Object> dates){
    System.out.println("dates calculate "+dates.get(0).toString().replaceAll("[^-?0-9]+", ""));
    String reportIdentityNumber=dates.get(0).toString().replaceAll("[^-?0-9]+", "");
    String technicianIdentity=dates.get(1).toString().replaceAll("[^-?0-9]+", "");
    String hourInit=dates.get(2).toString().replaceAll("[^-?0-9]+", "");
    String dateInit=dates.get(3).toString().replaceAll("[^-?0-9]+", "");
    String hourFinish=dates.get(4).toString().replaceAll("[^-?0-9]+", "");
    String dateFinish=dates.get(5).toString().replaceAll("[^-?0-9]+", "");
    String numWeek=dates.get(6).toString().replaceAll("[^-?0-9]+", "");


    int hourInitInt= Integer.parseInt(hourInit.substring(1));
    int hourFinishInt=Integer.parseInt(hourFinish.substring(1));
    int auxHourNormal;
    NonEmptyString horaNormalNonEmptyString;
    System.out.println(hourInitInt+" "+hourFinishInt);
    if(hourInitInt>600&&hourFinishInt<2000){
        auxHourNormal=hourFinishInt-hourInitInt;
        horaNormalNonEmptyString= new NonEmptyString(String.valueOf(auxHourNormal));
        System.out.println(horaNormalNonEmptyString);
    }

    if(hourInitInt>1900 && hourFinishInt<800){{
        auxHourNormal=hourFinishInt-hourInitInt;
        horaNormalNonEmptyString= new NonEmptyString(String.valueOf(auxHourNormal));
        System.out.println(horaNormalNonEmptyString);
    }
        if(hourInitInt>1900 && hourFinishInt<800){
            auxHourNormal=hourFinishInt-hourInitInt;
            horaNormalNonEmptyString= new NonEmptyString(String.valueOf(auxHourNormal));
            System.out.println(horaNormalNonEmptyString);

    }}}
}
