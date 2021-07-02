package co.com.ias.hoursWorkCalculator.report.application.ports.out;

import co.com.ias.hoursWorkCalculator.report.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportdentityNumber;

import java.util.Collection;
import java.util.Optional;

public interface ReportWeeklyRepository {
    Optional<ReportWeekly> getReportWeeklyById(ReportdentityNumber reportdentityNumber);

    void storeReportWeekly(ReportWeekly report);

    Collection<ReportWeekly> listReportsWeekly(int limit, int skip);

    Integer countReportsWeekly();
}
