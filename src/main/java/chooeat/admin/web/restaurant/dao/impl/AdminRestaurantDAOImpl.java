package chooeat.admin.web.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;

import chooeat.admin.web.restaurant.dao.RestaurantDAO;
import chooeat.admin.web.restaurant.pojo.AdminRestaurantVO;
import chooeat.admin.web.restaurant.pojo.AdminRestaurantPOJO;


@Repository
@Import(DataSourceAutoConfiguration.class)
public class AdminRestaurantDAOImpl implements RestaurantDAO{
	
	@Autowired
	private DataSource dataSource;
	

	@Override
	public AdminRestaurantVO findByResId(String resId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int register(List<String> values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByRestaurantId(AdminRestaurantVO RestaurantVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AdminRestaurantVO> searchRestaurants(String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminRestaurantVO> login(String account, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findfollow(String resAcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findrestype(String resAcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findprod(String resAcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findcomment(String resAcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findmyself(String resAcc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<AdminRestaurantVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminRestaurantPOJO> selectAllPOJO() {		
		final String sql = "SELECT r.*, rt.res_type_id, rt.res_type_name\r\n"
				+ "FROM restaurant r\r\n"
				+ "JOIN (\r\n"
				+ "    SELECT restaurant_id, MIN(res_type_id) AS min_res_type_id\r\n"
				+ "    FROM res_type_detail\r\n"
				+ "    GROUP BY restaurant_id\r\n"
				+ ") min_res_type ON r.restaurant_id = min_res_type.restaurant_id\r\n"
				+ "JOIN res_type rt ON min_res_type.min_res_type_id = rt.res_type_id;";
		
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

			List<AdminRestaurantPOJO> list = new ArrayList<AdminRestaurantPOJO>();

			try(ResultSet rs = stmt.executeQuery();){
				while (rs.next()) {
					AdminRestaurantPOJO restaurant = new AdminRestaurantPOJO();
					restaurant.setRestaurantId(rs.getInt("restaurant_id"));
		            restaurant.setResState(rs.getInt("res_state"));
		            restaurant.setResName(rs.getString("res_name"));
		            restaurant.setResType(rs.getString("res_type_name"));
					list.add(restaurant);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AdminRestaurantPOJO> selectAllPOJOByResId(String resId) {
		final String sql = "SELECT r.*, rt.res_type_id, rt.res_type_name\r\n"
				+ "FROM restaurant r\r\n"
				+ "JOIN (\r\n"
				+ "    SELECT restaurant_id, MIN(res_type_id) AS min_res_type_id\r\n"
				+ "    FROM res_type_detail\r\n"
				+ "    GROUP BY restaurant_id\r\n"
				+ ") min_res_type ON r.restaurant_id = min_res_type.restaurant_id\r\n"
				+ "JOIN res_type rt ON min_res_type.min_res_type_id = rt.res_type_id\r\n"
				+ "WHERE r.restaurant_id LIKE ?;";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, resId);
			ResultSet rs = stmt.executeQuery();

			List<AdminRestaurantPOJO> list = new ArrayList<AdminRestaurantPOJO>();

			while (rs.next()) {
				AdminRestaurantPOJO restaurant = new AdminRestaurantPOJO();
				restaurant.setRestaurantId(rs.getInt("restaurant_id"));
	            restaurant.setResState(rs.getInt("res_state"));
	            restaurant.setResName(rs.getString("res_name"));
	            restaurant.setResType(rs.getString("res_type_name"));
				list.add(restaurant);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<AdminRestaurantPOJO> selectAllPOJOByResName(String resName) {
		final String sql = "SELECT r.*, rt.res_type_id, rt.res_type_name\r\n"
				+ "FROM restaurant r\r\n"
				+ "JOIN (\r\n"
				+ "    SELECT restaurant_id, MIN(res_type_id) AS min_res_type_id\r\n"
				+ "    FROM res_type_detail\r\n"
				+ "    GROUP BY restaurant_id\r\n"
				+ ") min_res_type ON r.restaurant_id = min_res_type.restaurant_id\r\n"
				+ "JOIN res_type rt ON min_res_type.min_res_type_id = rt.res_type_id\r\n"
				+ "WHERE r.res_name LIKE ?;";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, resName);
			ResultSet rs = stmt.executeQuery();

			List<AdminRestaurantPOJO> list = new ArrayList<AdminRestaurantPOJO>();

			while (rs.next()) {
				AdminRestaurantPOJO restaurant = new AdminRestaurantPOJO();
				restaurant.setRestaurantId(rs.getInt("restaurant_id"));
	            restaurant.setResState(rs.getInt("res_state"));
	            restaurant.setResName(rs.getString("res_name"));
	            restaurant.setResType(rs.getString("res_type_name"));
				list.add(restaurant);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
