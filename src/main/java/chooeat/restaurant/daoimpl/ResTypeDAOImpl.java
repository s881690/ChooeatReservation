package chooeat.restaurant.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import chooeat.restaurant.dao.ResTypeDAO;
import chooeat.restaurant.model.vo.RestaurantVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ResTypeDAOImpl implements ResTypeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RestaurantVO> searchrestaurantbytag(String tag) {
		String sql = "SELECT * FROM res_type " +
				"JOIN res_type_detail ON res_type.res_type_id = res_type_detail.res_type_id " +
				"JOIN restaurant ON res_type_detail.restaurant_id = restaurant.restaurant_id " +
				"WHERE res_type_name = ?";

		return jdbcTemplate.query(sql, new Object[]{tag}, new RowMapper<RestaurantVO>() {
			@Override
			public RestaurantVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantVO restaurant = new RestaurantVO();
				restaurant.setRestaurantId(rs.getInt("restaurant_id"));
				restaurant.setResAcc(rs.getString("res_acc"));
				restaurant.setResPass(rs.getString("res_pass"));
				restaurant.setResState(rs.getInt("res_state"));
				restaurant.setResName(rs.getString("res_name"));
				restaurant.setResAdd(rs.getString("res_add"));
				restaurant.setResTel(rs.getString("res_tel"));
				restaurant.setResEmail(rs.getString("res_email"));
				restaurant.setResWeb(rs.getString("res_web"));
				restaurant.setResTimestamp(rs.getTimestamp("res_timestamp"));
				restaurant.setResStartTime(rs.getTime("res_start_time"));
				restaurant.setResEndTime(rs.getTime("res_end_time"));
				restaurant.setResTexId(rs.getString("res_tex_id"));
				restaurant.setResOwnerName(rs.getString("res_owner_name"));
				restaurant.setResSeatNumber(rs.getInt("res_seat_number"));
				restaurant.setResIntro(rs.getString("res_intro"));
				restaurant.setSingleMeal(rs.getBoolean("single_meal"));
				restaurant.setResTotalScore(rs.getInt("res_total_score"));
				restaurant.setResTotalNumber(rs.getInt("res_total_number"));
				restaurant.setResMaxNum(rs.getInt("res_max_num"));

				byte[] photoBytes = rs.getBytes("res_photo");
				if (photoBytes != null && photoBytes.length > 0) {
					Byte[] photoWrapper = new Byte[photoBytes.length];
					for (int i = 0; i < photoBytes.length; i++) {
						photoWrapper[i] = photoBytes[i];
					}
					restaurant.setResPhoto(photoWrapper);
				} else {
					restaurant.setResPhoto(null);
				}

				return restaurant;
			}
		});
	}
}
