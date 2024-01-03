package chooeat.reservation.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ResultRowMapper2 implements RowMapper<Result> {

	@Override
	public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
		Result result = new Result(); // 創建新的Result物件
		result.setRestaurantName(rs.getString("res_name"));
		result.setResStartTime(rs.getTimestamp("reservation_date_starttime").toGMTString());
		result.setResEndTime(rs.getTimestamp("reservation_date_endtime").toGMTString());
		result.setReservedPeople(rs.getInt("reservation_number"));
		
		return result;
	}

}
