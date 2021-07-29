package co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out;

import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;

import java.util.Collection;
import java.util.Optional;

public interface ReportWeeklyRepository {
    Optional<ReportWeekly> getReportWeeklyById(TechnicianIdentityNumber technicianIdentityNumber);

//    Optional<ServiceReport> getReportById(TechnicianIdentityNumber technicianIdentityNumber);

    Optional<ServiceReport> getReportById(ReportWeekly reportWeekly);

    Collection<ServiceReport> listReports();



    Optional<ReportWeekly> remove(ReportWeekly reportWeekly);

    void storeReportWeekly(ReportWeekly report);

    Collection<ReportWeekly> listReportsWeekly();



    Integer countReportsWeekly();
}
