package chooeat.reservation.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

public class HourlySeatRowMapper implements RowMapper<HourlySeat> {

	@Override
	public HourlySeat mapRow(ResultSet rs, int rowNum) throws SQLException {
		HourlySeat hourlySeat = new HourlySeat();
		hourlySeat.setHour(rs.getInt("hour"));
		hourlySeat.setRemainSeat(rs.getInt("remaining_seats"));
		return hourlySeat;
	}
	

}
