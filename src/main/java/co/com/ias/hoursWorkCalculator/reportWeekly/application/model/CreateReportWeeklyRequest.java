package co.com.ias.hoursWorkCalculator.reportWeekly.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateReportWeeklyRequest implements ApplicationRequest {
    private String technicianIdentity;
    public String getNumWeek() {
        return numWeek;
    }

    public void setNumWeek(String numWeek) {
        this.numWeek = numWeek;
    }

    private String numWeek;
    public String getTechnicianIdentity() {
        return technicianIdentity;
    }

    public void setTechnicianIdentity(String technicianIdentity) {
        this.technicianIdentity = technicianIdentity;
    }


    @Override
    public String toString() {
        return "CreateReportWeeklyRequest{" +
                "technicianIdentity='" + technicianIdentity + '\'' +

                ", numWeek='" + numWeek + '\'' +

                '}';
    }


    public CreateReportWeeklyRequest(String technicianIdentity, String numWeek ) {
        this.technicianIdentity = technicianIdentity;
        this.numWeek = numWeek;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateReportWeeklyRequest that = (CreateReportWeeklyRequest) o;
        return Objects.equals(technicianIdentity, that.technicianIdentity) && Objects.equals(numWeek, that.numWeek);

    }

    @Override
    public int hashCode() {
        return Objects.hash(technicianIdentity, numWeek);
    }
}
