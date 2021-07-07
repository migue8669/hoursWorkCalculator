package co.com.ias.hoursWorkCalculator.report.application.ports.out;

import co.com.ias.hoursWorkCalculator.report.application.domain.ReportIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;

import java.util.Collection;
import java.util.Optional;

public interface ReportRepository {
    Optional<ServiceReport> getReportById(ReportIdentityNumber reportIdentityNumber);

    void storeReport(ServiceReport report);

    Collection<ServiceReport> listReports(int limit, int skip);

    Integer countReports();
}