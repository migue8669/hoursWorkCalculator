package co.com.ias.hoursWorkCalculator.report.infraestructure.adapters.out;

import co.com.ias.hoursWorkCalculator.report.application.domain.ReportdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryReportRepository implements ReportRepository {
    private final Map<ReportdentityNumber, ServiceReport> database = new HashMap<>();

    @Override
    public Optional<ServiceReport> getReportById(ReportdentityNumber reportdentityNumber) {
        ServiceReport serviceReport = database.get(reportdentityNumber);
        return Optional.ofNullable(serviceReport);
    }

    @Override
    public void storeReport(ServiceReport student) {
        database.put(student.getReportdentityNumber(), student);
    }

    @Override
    public Collection<ServiceReport> listReports(int limit, int skip) {
        return database.values();
    }

    @Override
    public Integer countReports() {
        return database.size();
    }

}