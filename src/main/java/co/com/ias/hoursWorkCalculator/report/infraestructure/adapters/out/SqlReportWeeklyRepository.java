package co.com.ias.hoursWorkCalculator.report.infraestructure.adapters.out;

import co.com.ias.hoursWorkCalculator.report.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportWeeklyRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
@Repository

public class SqlReportWeeklyRepository  implements ReportWeeklyRepository {
        private final JdbcTemplate jdbcTemplate;

        public SqlReportWeeklyRepository(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        private final RowMapper<ReportWeekly> reportRowMapper = (rs, rowNum) -> fromResultSet(rs);

        @Override
        public Optional<ReportWeekly> getReportWeeklyById(ReportdentityNumber reportdentityNumber) {
            final String sql = "SELECT * FROM REPORT_WEEKLY WHERE ID_NUMBER_REPORT = ?";
            PreparedStatementSetter preparedStatementSetter = ps -> {
                ps.setString(1, reportdentityNumber.getValue());
            };
            final ResultSetExtractor<Optional<ReportWeekly>> resultSetExtractor = rs -> {
                if (rs.next()) {
                    final ReportWeekly reportWeekly = fromResultSet(rs);
                    return Optional.of(reportWeekly);
                } else {
                    return Optional.empty();
                }
            };

            return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);
        }



    @Override
        public void storeReportWeekly(ReportWeekly reportWeekly) {
            jdbcTemplate.update(connection -> {
                final PreparedStatement preparedStatement = connection
                        .prepareStatement("INSERT INTO REPORT_WEEKLY (ID_NUMBER_REPORT, ID_NUMBER_TECHNICIAN, NORMAL_HOUR, NOCTURNAL_HOUR,SUNDAY_HOUR,EXTRA_NORMAL_HOUR,EXTRA_NOCTURNAL_HOUR,EXTRA_SUNDAY_HOUR) VALUES (?, ?, ?, ?,?,?,?,?)");

                preparedStatement.setString(1, reportWeekly.getReportdentityNumber().getValue());
                preparedStatement.setString(2, reportWeekly.getTechnicianIdentity().getValue());
                preparedStatement.setString(3, reportWeekly.getHour().getValue());
                preparedStatement.setString(4, reportWeekly.getNightHour().getValue());
                preparedStatement.setString(5, reportWeekly.getSundayHour().getValue());
                preparedStatement.setString(6, reportWeekly.getExtraHour().getValue());
                preparedStatement.setString(7, reportWeekly.getExtraNightHour().getValue());
                preparedStatement.setString(8, reportWeekly.getExtraSundayHour().getValue());


                return preparedStatement;
            });
        }

        @Override
        public Collection<ReportWeekly> listReportsWeekly(int limit, int skip) {
            final String sql = "SELECT * FROM REPORT_WEEKLY LIMIT ? OFFSET ?";
            return jdbcTemplate.query(sql, reportRowMapper, limit, skip);
        }

        @Override
        public Integer countReportsWeekly() {
            String sql = "select count(*) from REPORT_WEEKLY";
            return jdbcTemplate.queryForObject(sql, Integer.class);
        }


        private static ReportWeekly fromResultSet(ResultSet rs) throws SQLException {
            return ReportWeekly.parseReport(
                    rs.getString("ID_NUMBER_REPORT"),
                    rs.getString("ID_NUMBER_TECHNICIAN"),
                    rs.getString("NORMAL_HOUR"),
                    rs.getString("NOCTURNAL_HOUR"),
                    rs.getString("SUNDAY_HOUR"),
                    rs.getString("EXTRA_NORMAL_HOUR"),
                    rs.getString("EXTRA_NOCTURNAL_HOUR"),
                    rs.getString("EXTRA_SUNDAY_HOUR")



            ).get();
        }

}

