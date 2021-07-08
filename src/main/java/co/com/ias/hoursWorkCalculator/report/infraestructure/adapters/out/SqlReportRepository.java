package co.com.ias.hoursWorkCalculator.report.infraestructure.adapters.out;

import co.com.ias.hoursWorkCalculator.report.application.domain.ReportIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
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

@Repository("sql")

public class SqlReportRepository implements ReportRepository {
    private final JdbcTemplate jdbcTemplate;

    public SqlReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ServiceReport> reportRowMapper = (rs, rowNum) -> fromResultSet(rs);

    @Override
    public Optional<ServiceReport> getReportById(ReportIdentityNumber reportIdentityNumber) {
        final String sql = "SELECT * FROM REPORT WHERE ID_NUMBER_REPORT = ?";
        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, reportIdentityNumber.getValue());
        };
        final ResultSetExtractor<Optional<ServiceReport>> resultSetExtractor = rs -> {
            if (rs.next()) {
                final ServiceReport serviceReport = fromResultSet(rs);
                return Optional.of(serviceReport);
            } else {
                return Optional.empty();
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);
    }

    @Override
    public void storeReport(ServiceReport serviceReport) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO REPORT (ID_NUMBER_REPORT, ID_NUMBER_TECHNICIAN, DATE_INIT, DATE_FINISH,HOUR_INIT,HOUR_FINISH) VALUES (?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, serviceReport.getReportIdentityNumber().getValue());
            preparedStatement.setString(2, serviceReport.getTechnicianIdentity().getValue());
            preparedStatement.setString(3, serviceReport.getHourInit().getValue());
            preparedStatement.setString(4, serviceReport.getDateInit().getValue());
            preparedStatement.setString(5, serviceReport.getHourFinish().getValue());
            preparedStatement.setString(6, serviceReport.getDateFinish().getValue());



            return preparedStatement;
        });
    }

    @Override
    public Collection<ServiceReport> listReports(int limit, int skip) {
        final String sql = "SELECT * FROM REPORT LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, reportRowMapper, limit, skip);
    }

    @Override
    public Integer countReports() {
        String sql = "select count(*) from REPORT";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    private static ServiceReport fromResultSet(ResultSet rs) throws SQLException {
        return ServiceReport.parseReport(
                rs.getString("ID_NUMBER_REPORT"),
                rs.getString("ID_NUMBER_TECHNICIAN"),
                rs.getString("DATE_INIT"),
                rs.getString("DATE_FINISH"),
                rs.getString("HOUR_INIT"),
                rs.getString("HOUR_FINISH")



        ).get();
    }

}
