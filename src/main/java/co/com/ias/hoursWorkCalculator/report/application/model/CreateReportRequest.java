package co.com.ias.hoursWorkCalculator.report.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateReportRequest implements ApplicationRequest {
    private String technicianIdentity ;
    private String serviceIdentity;
    private String  dateAndTimeInit;
    private String  dateAndTimeFinish;
    public CreateReportRequest() {
    }
    public CreateReportRequest(String technicianIdentity, String serviceIdentity, String dateAndTimeInit, String dateAndTimeFinish) {
        this.technicianIdentity = technicianIdentity;
        this.serviceIdentity = serviceIdentity;
        this.dateAndTimeInit = dateAndTimeInit;
        this.dateAndTimeFinish = dateAndTimeFinish;
    }



    public String getTechnicianIdentity() {
        return technicianIdentity;
    }

    public void setTechnicianIdentity(String technicianIdentity) {
        this.technicianIdentity = technicianIdentity;
    }

    public String getServiceIdentity() {
        return serviceIdentity;
    }

    public void setServiceIdentity(String serviceIdentity) {
        this.serviceIdentity = serviceIdentity;
    }

    public String getDateAndTimeInit() {
        return dateAndTimeInit;
    }

    public void setDateAndTimeInit(String dateAndTimeInit) {
        this.dateAndTimeInit = dateAndTimeInit;
    }

    public String getDateAndTimeFinish() {
        return dateAndTimeFinish;
    }

    public void setDateAndTimeFinish(String dateAndTimeFinish) {
        this.dateAndTimeFinish = dateAndTimeFinish;
    }
    @Override
    public String toString() {
        return "CreateReportRequest{" +
                "technicianIdentity='" + technicianIdentity + '\'' +
                ", serviceIdentity='" + serviceIdentity + '\'' +
                ", dateAndTimeInit='" + dateAndTimeInit + '\'' +
                ", dateAndTimeFinish='" + dateAndTimeFinish + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateReportRequest that = (CreateReportRequest) o;
        return technicianIdentity.equals(that.technicianIdentity) &&
                serviceIdentity.equals(that.serviceIdentity) &&
                dateAndTimeInit.equals(that.dateAndTimeInit) &&
                dateAndTimeFinish.equals(that.dateAndTimeFinish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicianIdentity, serviceIdentity, dateAndTimeInit, dateAndTimeFinish);
    }
}
