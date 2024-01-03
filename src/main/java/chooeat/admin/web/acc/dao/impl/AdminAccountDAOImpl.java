package chooeat.admin.web.acc.dao.impl;

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

import chooeat.admin.web.acc.dao.AccountDAO;
import chooeat.admin.web.acc.pojo.AdminAccountVO;

@Repository
@Import(DataSourceAutoConfiguration.class)
public class AdminAccountDAOImpl implements AccountDAO{
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public int insert(AdminAccountVO VO) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public int update(AdminAccountVO VO) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public AdminAccountVO selectById(Integer accId) {
		final String sql = "SELECT * FROM account WHERE acc_id = ?; ";
		
		try(Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);){
			
			stmt.setInt(1, accId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				AdminAccountVO accountVO = new AdminAccountVO();
				accountVO.setAccId(rs.getInt("acc_id"));
				accountVO.setAccAcc(rs.getString("acc_acc"));
				accountVO.setAccPass(rs.getString("acc_pass"));
				accountVO.setAccName(rs.getString("acc_name"));
				accountVO.setAccNickname(rs.getString("acc_nickname"));
				accountVO.setAccPhone(rs.getString("acc_phone"));
				accountVO.setAccMail(rs.getString("acc_mail"));
				accountVO.setAccAdd1(rs.getString("acc_add1"));
				accountVO.setAccAdd2(rs.getString("acc_add2"));
				accountVO.setAccAdd3(rs.getString("acc_add3"));
				accountVO.setAccBirth(rs.getDate("acc_birth"));
				accountVO.setAccGender(rs.getInt("acc_gender"));
				accountVO.setAccPic(rs.getBytes("acc_pic"));
				accountVO.setAccText(rs.getString("acc_text"));
				accountVO.setAccTimestamp(rs.getTimestamp("acc_timestamp"));
				accountVO.setAccState(rs.getInt("acc_state"));
				accountVO.setOnlineStatus(rs.getInt("online_status"));
				return accountVO;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AdminAccountVO> selectAll(){
		String sql = "select * from account; ";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			List<AdminAccountVO> list = new ArrayList<AdminAccountVO>();
			while (rs.next()) {
				AdminAccountVO accountVO = new AdminAccountVO();
				accountVO.setAccId(rs.getInt("acc_id"));
				accountVO.setAccAcc(rs.getString("acc_acc"));
				accountVO.setAccPass(rs.getString("acc_pass"));
				accountVO.setAccName(rs.getString("acc_name"));
				accountVO.setAccNickname(rs.getString("acc_nickname"));
				accountVO.setAccPhone(rs.getString("acc_phone"));
				accountVO.setAccMail(rs.getString("acc_mail"));
				accountVO.setAccAdd1(rs.getString("acc_add1"));
				accountVO.setAccAdd2(rs.getString("acc_add2"));
				accountVO.setAccAdd3(rs.getString("acc_add3"));
				accountVO.setAccBirth(rs.getDate("acc_birth"));
				accountVO.setAccGender(rs.getInt("acc_gender"));
				accountVO.setAccPic(rs.getBytes("acc_pic"));
				accountVO.setAccText(rs.getString("acc_text"));
				accountVO.setAccTimestamp(rs.getTimestamp("acc_timestamp"));
				accountVO.setAccState(rs.getInt("acc_state"));
				accountVO.setOnlineStatus(rs.getInt("online_status"));
				list.add(accountVO);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public List<AdminAccountVO> selectAllByAccId(String accId) {
		final String sql = "SELECT * FROM account WHERE acc_id like ? ORDER BY acc_id; ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, accId);
			ResultSet rs = stmt.executeQuery();

			List<AdminAccountVO> list = new ArrayList<AdminAccountVO>();

			while (rs.next()) {
				AdminAccountVO accountVO = new AdminAccountVO();
				accountVO.setAccId(rs.getInt("acc_id"));
				accountVO.setAccAcc(rs.getString("acc_acc"));
				accountVO.setAccPass(rs.getString("acc_pass"));
				accountVO.setAccName(rs.getString("acc_name"));
				accountVO.setAccNickname(rs.getString("acc_nickname"));
				accountVO.setAccPhone(rs.getString("acc_phone"));
				accountVO.setAccMail(rs.getString("acc_mail"));
				accountVO.setAccAdd1(rs.getString("acc_add1"));
				accountVO.setAccAdd2(rs.getString("acc_add2"));
				accountVO.setAccAdd3(rs.getString("acc_add3"));
				accountVO.setAccBirth(rs.getDate("acc_birth"));
				accountVO.setAccGender(rs.getInt("acc_gender"));
				accountVO.setAccPic(rs.getBytes("acc_pic"));
				accountVO.setAccText(rs.getString("acc_text"));
				accountVO.setAccTimestamp(rs.getTimestamp("acc_timestamp"));
				accountVO.setAccState(rs.getInt("acc_state"));
				accountVO.setOnlineStatus(rs.getInt("online_status"));
				list.add(accountVO);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AdminAccountVO> selectAllByAccAcc(String accAcc) {
		final String sql = "SELECT * FROM account WHERE acc_acc like ? ORDER BY acc_id; ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, accAcc);
			ResultSet rs = stmt.executeQuery();

			List<AdminAccountVO> list = new ArrayList<AdminAccountVO>();

			while (rs.next()) {
				AdminAccountVO accountVO = new AdminAccountVO();
				accountVO.setAccId(rs.getInt("acc_id"));
				accountVO.setAccAcc(rs.getString("acc_acc"));
				accountVO.setAccPass(rs.getString("acc_pass"));
				accountVO.setAccName(rs.getString("acc_name"));
				accountVO.setAccNickname(rs.getString("acc_nickname"));
				accountVO.setAccPhone(rs.getString("acc_phone"));
				accountVO.setAccMail(rs.getString("acc_mail"));
				accountVO.setAccAdd1(rs.getString("acc_add1"));
				accountVO.setAccAdd2(rs.getString("acc_add2"));
				accountVO.setAccAdd3(rs.getString("acc_add3"));
				accountVO.setAccBirth(rs.getDate("acc_birth"));
				accountVO.setAccGender(rs.getInt("acc_gender"));
				accountVO.setAccPic(rs.getBytes("acc_pic"));
				accountVO.setAccText(rs.getString("acc_text"));
				accountVO.setAccTimestamp(rs.getTimestamp("acc_timestamp"));
				accountVO.setAccState(rs.getInt("acc_state"));
				accountVO.setOnlineStatus(rs.getInt("online_status"));
				list.add(accountVO);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AdminAccountVO> selectAllByAccName(String accName) {
		final String sql = "SELECT * FROM account WHERE acc_name like ? ORDER BY acc_id; ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, accName);
			ResultSet rs = stmt.executeQuery();

			List<AdminAccountVO> list = new ArrayList<AdminAccountVO>();

			while (rs.next()) {
				AdminAccountVO accountVO = new AdminAccountVO();
				accountVO.setAccId(rs.getInt("acc_id"));
				accountVO.setAccAcc(rs.getString("acc_acc"));
				accountVO.setAccPass(rs.getString("acc_pass"));
				accountVO.setAccName(rs.getString("acc_name"));
				accountVO.setAccNickname(rs.getString("acc_nickname"));
				accountVO.setAccPhone(rs.getString("acc_phone"));
				accountVO.setAccMail(rs.getString("acc_mail"));
				accountVO.setAccAdd1(rs.getString("acc_add1"));
				accountVO.setAccAdd2(rs.getString("acc_add2"));
				accountVO.setAccAdd3(rs.getString("acc_add3"));
				accountVO.setAccBirth(rs.getDate("acc_birth"));
				accountVO.setAccGender(rs.getInt("acc_gender"));
				accountVO.setAccPic(rs.getBytes("acc_pic"));
				accountVO.setAccText(rs.getString("acc_text"));
				accountVO.setAccTimestamp(rs.getTimestamp("acc_timestamp"));
				accountVO.setAccState(rs.getInt("acc_state"));
				accountVO.setOnlineStatus(rs.getInt("online_status"));
				list.add(accountVO);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
