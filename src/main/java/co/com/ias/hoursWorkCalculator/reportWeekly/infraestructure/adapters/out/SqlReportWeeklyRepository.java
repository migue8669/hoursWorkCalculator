package co.com.ias.hoursWorkCalculator.reportWeekly.infraestructure.adapters.out;

import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class SqlReportWeeklyRepository  implements ReportWeeklyRepository {
    private final JdbcTemplate jdbcTemplate;


    public SqlReportWeeklyRepository(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        private final RowMapper<ReportWeekly> reportRowMapper = (rs, rowNum) -> fromResultSet(rs);
    private final RowMapper<ServiceReport> reportServiceRowMapper = (rs, rowNum) -> fromResultSetRS(rs);
    static ArrayList<Object> dates = new ArrayList<Object>();

    private ReportWeekly fromResultSet(ResultSet rs)  throws SQLException {
        return ReportWeekly.WeeklyReport(
                rs.getString("ID_NUMBER_TECHNICIAN"),
                rs.getString("NORMAL_HOUR"),
                rs.getString("NOCTURNAL_HOUR"),
                rs.getString("SUNDAY_HOUR"),
                rs.getString("EXTRA_NORMAL_HOUR"),
                rs.getString("EXTRA_NOCTURNAL_HOUR"),
                rs.getString("EXTRA_SUNDAY_HOUR"),
                rs.getString("NUM_WEEK")



        ).get();
    }

    @Override
    public Optional<ReportWeekly> getReportWeeklyById(TechnicianIdentityNumber technicianIdentityNumber) {
        System.out.println("sqlReport");
            final String sql = "SELECT * FROM REPORT_WEEKLY WHERE ID_NUMBER_TECHNICIAN = ?";
            PreparedStatementSetter preparedStatementSetter = ps -> {
                System.out.println(ps);

                ps.setString(1, technicianIdentityNumber.getValue());
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
    public Optional<ServiceReport> getReportById(TechnicianIdentityNumber technicianIdentityNumber) {
        System.out.println("getReportByIdSqlReportWeeklyRep");
        System.out.println(technicianIdentityNumber);
        final String sql = "SELECT * FROM REPORT WHERE ID_NUMBER_TECHNICIAN = ?";
        System.out.println(sql);

        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1,technicianIdentityNumber.getValue());
            System.out.println(ps);
        };
        final ResultSetExtractor<Optional<ServiceReport>> resultSetExtractor = rs -> {

            if (rs.next()) {
                final ServiceReport serviceReport = fromResultSetRS(rs);
                System.out.println("if rs.next"+serviceReport);
                return Optional.of(serviceReport);
            } else {
                return Optional.empty();
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);    }

    @Override
    public Collection<ServiceReport> listReports() {
        final String sql = "SELECT * FROM REPORT";
        System.out.println("ListReports"+jdbcTemplate.query(sql, reportServiceRowMapper));
        return jdbcTemplate.query(sql, reportServiceRowMapper);
    }

    @Override
    public void storeReportWeekly(ReportWeekly report) {
        System.out.println("storeReportWeekly");
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO REPORT_WEEKLY (ID_NUMBER_TECHNICIAN, NORMAL_HOUR, NOCTURNAL_HOUR,SUNDAY_HOUR,EXTRA_NORMAL_HOUR,EXTRA_NOCTURNAL_HOUR,EXTRA_SUNDAY_HOUR,NUM_WEEK) VALUES (?, ?, ?, ?,?,?,?,?)");
            preparedStatement.setString(1, report.getTechnicianIdentity().getValue());

            preparedStatement.setString(2, report.getHour().getValue());
            preparedStatement.setString(3, report.getNightHour().getValue());
            preparedStatement.setString(4, report.getSundayHour().getValue());
            preparedStatement.setString(5, report.getExtraHour().getValue());
            preparedStatement.setString(6, report.getExtraNightHour().getValue());
            preparedStatement.setString(7, report.getExtraSundayHour().getValue());
            preparedStatement.setString(8, report.getNumWeek().getValue());



            return preparedStatement;
        });
    }

    @Override
        public Collection<ReportWeekly> listReportsWeekly( ) {
            final String sql = "SELECT * FROM REPORT_WEEKLY";
            return jdbcTemplate.query(sql, reportRowMapper);
        }



    @Override
        public Integer countReportsWeekly() {
            String sql = "select count(*) from REPORT_WEEKLY";
            return jdbcTemplate.queryForObject(sql, Integer.class);
        }


    private static ServiceReport fromResultSetRS(ResultSet rs) throws SQLException {


            Object date = new Object[7];
            for (int i = 1; i <= 7; i++) {
                date = rs.getArray(i);
                dates.add(date);
            }
 //       Calculator calculator = new Calculator();
   //         calculator.calculate(dates);
     //   System.out.println("dates" +dates);

    return ServiceReport.parseReport(
                rs.getString("ID_NUMBER_REPORT"),

                rs.getString("ID_NUMBER_TECHNICIAN"),

                rs.getString("DATE_INIT"),
                rs.getString("DATE_FINISH"),
                rs.getString("HOUR_INIT"),
                rs.getString("HOUR_FINISH"),
                rs.getString("NUM_WEEK")





        ).get();
    }
}

