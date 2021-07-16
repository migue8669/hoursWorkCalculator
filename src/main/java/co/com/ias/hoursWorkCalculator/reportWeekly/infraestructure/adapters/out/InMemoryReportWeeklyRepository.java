package co.com.ias.hoursWorkCalculator.reportWeekly.infraestructure.adapters.out;

import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;

import java.util.*;

public class InMemoryReportWeeklyRepository implements ReportWeeklyRepository {
    private final Map<NonEmptyString, ReportWeekly> database = new HashMap<>();
    private final Map<NonEmptyString, ServiceReport> databaseRep = new HashMap<>();
    @Override
    public Optional<ReportWeekly> getReportWeeklyById(NonEmptyString reportIdentityNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<ServiceReport> getReportById(NonEmptyString technicianIdentityNumber) {
        return Optional.empty();
    }

    @Override
    public Collection<ServiceReport> listReports() {
        System.out.println("db"+databaseRep);
        return databaseRep.values();
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
