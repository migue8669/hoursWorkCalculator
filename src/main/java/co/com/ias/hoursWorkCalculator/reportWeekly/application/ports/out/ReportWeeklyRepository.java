package co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out;

import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.TechnicianIdentityNumber;

import java.util.Collection;
import java.util.Optional;

public interface ReportWeeklyRepository {
    Optional<ReportWeekly> getReportWeeklyById(TechnicianIdentityNumber technicianIdentityNumber);


    void storeReportWeekly(ReportWeekly report);

    Collection<ReportWeekly> listReportsWeekly(int limit, int skip);

    Integer countReportsWeekly();
}
