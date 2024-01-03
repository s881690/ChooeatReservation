package chooeat.reservation.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class ReservationRowMapper implements RowMapper<ReservationVO> {


	@Override
	public ReservationVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setReservationDateStartTime(rs.getTimestamp("reservation_date_starttime"));
		reservationVO.setReservationDateEndTime(rs.getTimestamp("reservation_date_endtime"));
		return reservationVO;
	}

}
