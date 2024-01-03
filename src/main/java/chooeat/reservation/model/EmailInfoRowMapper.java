package chooeat.reservation.model;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmailInfoRowMapper implements RowMapper<EmailInfo>{

	@Override
	public EmailInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setReservationId(rs.getInt("reservation_id"));
		emailInfo.setRecipientName(rs.getString("acc_name"));
		emailInfo.setRecipient(rs.getString("acc_mail"));
		emailInfo.setReservationNumber(rs.getInt("reservation_number"));
		emailInfo.setReservationTime(rs.getTimestamp("reservation_date_starttime").toString());
		emailInfo.setRestaurantAddress(rs.getString("res_add"));
		emailInfo.setRestaurantPhone(rs.getString("res_tel"));
		emailInfo.setIsNotify(rs.getInt("isNotify"));
		emailInfo.setRestaurantName(rs.getString("res_name"));
		
		return emailInfo;
	}

}
