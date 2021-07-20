package co.com.ias.hoursWorkCalculator.report.application.ports.out;

import co.com.ias.hoursWorkCalculator.commons.ReportIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;

import java.util.Collection;
import java.util.Optional;

public interface ReportRepository {
    Optional<ServiceReport> getReportByIdTechnician(TechnicianIdentityNumber technicianIdentityNumber);

    Optional<ServiceReport> getReportById(ReportIdentityNumber reportIdentityNumber);

    void storeReport(ServiceReport report);

    Collection<ServiceReport> listReports();

    Integer countReports();
}