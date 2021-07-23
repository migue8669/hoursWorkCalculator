package co.com.ias.hoursWorkCalculator.report.infraestructure.adapters.out;

import co.com.ias.hoursWorkCalculator.commons.ReportIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryReportRepository implements ReportRepository {
    private final Map<ReportIdentityNumber, ServiceReport> database = new HashMap<>();

    @Override
    public Optional<ServiceReport> getReportByIdTechnician(TechnicianIdentityNumber technicianIdentityNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<ServiceReport> getReportById(ReportIdentityNumber reportIdentityNumber) {
        ServiceReport serviceReport = database.get(reportIdentityNumber);
        return Optional.ofNullable(serviceReport);
    }

    @Override
    public void storeReport(ServiceReport serviceReport) {
        database.put(serviceReport.getReportIdentityNumber(), serviceReport);
    }

    @Override
    public Collection<ServiceReport> listReports() {
        return database.values();
    }

    @Override
    public Integer countReports() {
        return database.size();
    }

}