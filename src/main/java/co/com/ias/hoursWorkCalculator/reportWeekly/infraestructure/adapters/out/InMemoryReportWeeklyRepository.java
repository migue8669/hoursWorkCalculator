package co.com.ias.hoursWorkCalculator.reportWeekly.infraestructure.adapters.out;

import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryReportWeeklyRepository implements ReportWeeklyRepository {
    private final Map<TechnicianIdentityNumber, ReportWeekly> database = new HashMap<>();

    @Override
    public Optional<ReportWeekly> getReportWeeklyById(TechnicianIdentityNumber reportIdentityNumber) {
        return Optional.empty();
    }

    @Override
    public void storeReportWeekly(ReportWeekly report) {
database.put(report.getTechnicianIdentity(),report);
    }

    @Override
    public Collection<ReportWeekly> listReportsWeekly(int limit, int skip) {
        return database.values();
    }

    @Override
    public Integer countReportsWeekly() {
        return database.size();
    }
}
