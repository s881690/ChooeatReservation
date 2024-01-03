package chooeat.reservation.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

public class ResultRowMapper implements RowMapper<Result> {
	

	@Override
	public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
		Result result = new Result(); // 創建新的Result物件
		result.setMember(rs.getString("acc_name"));
		result.setResStartTime(rs.getTimestamp("reservation_date_starttime").toString());
		result.setReservedPeople(rs.getInt("reservation_number"));
		
		return result;
	}

}
