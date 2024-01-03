package chooeat.admin.web.resType.dao.impl;

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

import chooeat.admin.web.resType.dao.ResTypeDAO;
import chooeat.admin.web.resType.pojo.ResTypeVO;

@Repository
@Import(DataSourceAutoConfiguration.class)
public class AdminResTypeDAOImpl implements ResTypeDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public int insert(ResTypeVO resType) {
		final String sql = "INSERT INTO res_type (res_type_name) VALUES (?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql)) {
			ptmt.setString(1, resType.getResTypeName());
			return ptmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteById(Integer resTypeId) {
		final String sql = "DELETE FROM res_type WHERE res_type_id = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql)) {
			ptmt.setInt(1, resTypeId);
			return ptmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(ResTypeVO VO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResTypeVO selectById(Integer resTypeId) {
		final String sql = "SELECT * FROM res_type WHERE res_type_id = ?; ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, resTypeId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ResTypeVO resType = new ResTypeVO();
					resType.setResTypeId(rs.getInt("res_type_id"));
					resType.setResTypeName(rs.getString("res_type_name"));
					resType.setResTypeCount(null);
					return resType;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ResTypeVO selectByResTypeName(String resTypeName) {
		final String sql = "SELECT * FROM res_type WHERE res_type_name = ?; ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, resTypeName);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ResTypeVO resType = new ResTypeVO();
					resType.setResTypeId(rs.getInt("res_type_id"));
					resType.setResTypeName(rs.getString("res_type_name"));
					resType.setResTypeCount(null);
					return resType;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public List<ResTypeVO> selectAll() {
		final String sql = "SELECT rt.*, COUNT(rtd.res_type_id) AS count\r\n" + "FROM res_type rt\r\n"
				+ "LEFT JOIN res_type_detail rtd ON rt.res_type_id = rtd.res_type_id\r\n" + "GROUP BY rt.res_type_id;";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql)) {

			List<ResTypeVO> resTypeList = new ArrayList<ResTypeVO>();

			try (ResultSet rs = ptmt.executeQuery()) {
				while (rs.next()) {
					ResTypeVO resType = new ResTypeVO();
					resType.setResTypeId(rs.getInt("res_type_id"));
					resType.setResTypeName(rs.getString("res_type_name"));
					resType.setResTypeCount(rs.getInt("count"));
					resTypeList.add(resType);
				}
				return resTypeList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ResTypeVO> selectAllByResTypeId(String resTypeId) {
		final String sql = "SELECT rt.*, COUNT(rtd.res_type_id) AS count\r\n" + "FROM res_type rt\r\n"
				+ "LEFT JOIN res_type_detail rtd ON rt.res_type_id = rtd.res_type_id\r\n"
				+ "WHERE rt.res_type_id LIKE ?\r\n" + "GROUP BY rt.res_type_id;";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql)) {
			ptmt.setString(1, resTypeId);
			ResultSet rs = ptmt.executeQuery();

			List<ResTypeVO> resTypeList = new ArrayList<ResTypeVO>();

			while (rs.next()) {
				ResTypeVO resType = new ResTypeVO();
				resType.setResTypeId(rs.getInt("res_type_id"));
				resType.setResTypeName(rs.getString("res_type_name"));
				resType.setResTypeCount(rs.getInt("count"));
				resTypeList.add(resType);
			}
			return resTypeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ResTypeVO> selectAllByResTypeName(String resTypeName) {
		final String sql = "SELECT rt.*, COUNT(rtd.res_type_id) AS count\r\n" + "FROM res_type rt\r\n"
				+ "LEFT JOIN res_type_detail rtd ON rt.res_type_id = rtd.res_type_id\r\n"
				+ "WHERE rt.res_type_name LIKE ?\r\n" + "GROUP BY rt.res_type_id;";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql)) {
			ptmt.setString(1, resTypeName);
			ResultSet rs = ptmt.executeQuery();

			List<ResTypeVO> resTypeList = new ArrayList<ResTypeVO>();

			while (rs.next()) {
				ResTypeVO resType = new ResTypeVO();
				resType.setResTypeId(rs.getInt("res_type_id"));
				resType.setResTypeName(rs.getString("res_type_name"));
				resType.setResTypeCount(rs.getInt("count"));
				resTypeList.add(resType);
			}
			return resTypeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
