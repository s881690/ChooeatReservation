package chooeat.reservation.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class reservedListRowMapper implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Integer i = rs.getInt("reservation_hour");
		
		return i;
	}

}
