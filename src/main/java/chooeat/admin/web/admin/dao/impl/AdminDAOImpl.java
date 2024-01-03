package chooeat.admin.web.admin.dao.impl;

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

import chooeat.admin.web.admin.dao.AdminDAO;
import chooeat.admin.web.admin.pojo.AdminVO;

@Repository
@Import(DataSourceAutoConfiguration.class)
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public int insert(AdminVO admin) {
		final String sql = "INSERT INTO admin(admin_acc, admin_name, admin_pass, admin_permission) value(?, ?, ?, ?)";
		try(Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql)){
			
			ptmt.setString(1, admin.getAdminAcc());
			ptmt.setString(2, admin.getAdminName());
			ptmt.setString(3, admin.getAdminPass());
			ptmt.setInt(4, admin.getAdminPermission());
			return ptmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public int deleteById(Integer id) {

		return 0;
	}

	@Override
	public int update(AdminVO admin) {			
		final String sql = "UPDATE admin SET admin_acc = ?, admin_name = ?, admin_pass = ?, admin_timestamp = NOW(), admin_permission = ? WHERE admin_id = ?; ";
			try (Connection conn = dataSource.getConnection();
				PreparedStatement ptmt = conn.prepareStatement(sql)) {
				
				ptmt.setString(1, admin.getAdminAcc());
				ptmt.setString(2, admin.getAdminName());
				ptmt.setString(3, admin.getAdminPass());
				ptmt.setInt(4, admin.getAdminPermission());
				ptmt.setInt(5, admin.getAdminId());
				return ptmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
	}

	@Override
	public AdminVO selectById(Integer adminId) {
		final String sql = "SELECT * FROM admin WHERE admin_id = ?; ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, adminId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					AdminVO admin = new AdminVO();
					admin.setAdminId(rs.getInt("admin_id"));
					admin.setAdminAcc(rs.getString("admin_acc"));
					admin.setAdminName(rs.getString("admin_name"));
					admin.setAdminPass(rs.getString("admin_pass"));
					admin.setAdminTimestamp(rs.getTimestamp("admin_timestamp"));
					admin.setAdminPermission(rs.getInt("admin_permission"));
					return admin;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AdminVO> selectAll() {
		final String sql = "SELECT * FROM admin ORDER BY admin_id; ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql)) {

			List<AdminVO> adminList = new ArrayList<AdminVO>();

			try (ResultSet rs = ptmt.executeQuery()) {
				while (rs.next()) {
					AdminVO adminVO = new AdminVO();
					adminVO.setAdminId(rs.getInt("admin_id"));
					adminVO.setAdminAcc(rs.getString("admin_acc"));
					adminVO.setAdminName(rs.getString("admin_name"));
					adminVO.setAdminPass(rs.getString("admin_pass"));
					adminVO.setAdminTimestamp(rs.getTimestamp("admin_timestamp"));
					adminVO.setAdminPermission(rs.getInt("admin_permission"));

					adminList.add(adminVO);
				}
				return adminList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AdminVO selectByAdminName(String adminName) {
		final String sql = "SELECT * FROM admin WHERE admin_name = ?; ";
		try(Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql);) {
			ptmt.setString(1, adminName);
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next()) {
				AdminVO adminVO = new AdminVO();
				adminVO.setAdminId(rs.getInt("admin_id"));
				adminVO.setAdminAcc(rs.getString("admin_acc"));
				adminVO.setAdminName(rs.getString("admin_name"));
				adminVO.setAdminPass(rs.getString("admin_pass"));
				adminVO.setAdminTimestamp(rs.getTimestamp("admin_timestamp"));
				adminVO.setAdminPermission(rs.getInt("admin_permission"));
				return adminVO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AdminVO selectByAdminAcc(String adminAcc) {
		final String sql = "SELECT * FROM admin where admin_acc = ?; ";
		try(Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql);) {
			ptmt.setString(1, adminAcc);
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next()) {
				AdminVO adminVO = new AdminVO();
				adminVO.setAdminId(rs.getInt("admin_id"));
				adminVO.setAdminAcc(rs.getString("admin_acc"));
				adminVO.setAdminName(rs.getString("admin_name"));
				adminVO.setAdminPass(rs.getString("admin_pass"));
				adminVO.setAdminTimestamp(rs.getTimestamp("admin_timestamp"));
				adminVO.setAdminPermission(rs.getInt("admin_permission"));
				return adminVO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AdminVO> selectAllByAdminName(String searchCondition) {
		final String sql = "SELECT * FROM admin WHERE admin_name like ? ORDER BY admin_id; ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql);) {
			ptmt.setString(1, searchCondition);
			ResultSet rs = ptmt.executeQuery();

			List<AdminVO> adminList = new ArrayList<AdminVO>();

			while (rs.next()) {
				AdminVO adminVO = new AdminVO();
				adminVO.setAdminId(rs.getInt("admin_id"));
				adminVO.setAdminAcc(rs.getString("admin_acc"));
				adminVO.setAdminName(rs.getString("admin_name"));
				adminVO.setAdminPass(rs.getString("admin_pass"));
				adminVO.setAdminTimestamp(rs.getTimestamp("admin_timestamp"));
				adminVO.setAdminPermission(rs.getInt("admin_permission"));
				adminList.add(adminVO);
			}
			return adminList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<AdminVO> selectAllByAdminAcc(String searchCondition) {
		final String sql = "SELECT * FROM admin WHERE admin_acc like ? ORDER BY admin_id; ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql);) {
			ptmt.setString(1, searchCondition);
			ResultSet rs = ptmt.executeQuery();

			List<AdminVO> adminList = new ArrayList<AdminVO>();

			while (rs.next()) {
				AdminVO adminVO = new AdminVO();
				adminVO.setAdminId(rs.getInt("admin_id"));
				adminVO.setAdminAcc(rs.getString("admin_acc"));
				adminVO.setAdminName(rs.getString("admin_name"));
				adminVO.setAdminPass(rs.getString("admin_pass"));
				adminVO.setAdminTimestamp(rs.getTimestamp("admin_timestamp"));
				adminVO.setAdminPermission(rs.getInt("admin_permission"));
				adminList.add(adminVO);
			}
			return adminList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AdminVO selectForLogin(String adminAcc, String adminPass) {
		final String sql = "SELECT * FROM admin WHERE admin_acc = ? and admin_pass = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ptmt = conn.prepareStatement(sql)) {

			ptmt.setString(1, adminAcc);
			ptmt.setString(2, adminPass);

			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				AdminVO adminVO = new AdminVO();
				adminVO.setAdminId(rs.getInt("admin_id"));
				adminVO.setAdminAcc(rs.getString("admin_acc"));
				adminVO.setAdminName(rs.getString("admin_name"));
				adminVO.setAdminPass(rs.getString("admin_pass"));
				adminVO.setAdminTimestamp(rs.getTimestamp("admin_timestamp"));
				adminVO.setAdminPermission(rs.getInt("admin_permission"));
				return adminVO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
