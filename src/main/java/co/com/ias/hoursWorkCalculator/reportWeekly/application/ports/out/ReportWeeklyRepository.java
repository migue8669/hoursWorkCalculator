package co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out;

import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.TechnicianIdentityNumber;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReportWeeklyRepository {
    Optional<ReportWeekly> getReportWeeklyById(NonEmptyString technicianIdentityNumber);

    Optional<ServiceReport> getReportById(NonEmptyString technicianIdentityNumber);

    Collection<ServiceReport> listReports();

    void storeReportWeekly(ReportWeekly report);

    Collection<ReportWeekly> listReportsWeekly(int limit, int skip);

    Integer countReportsWeekly();
}
