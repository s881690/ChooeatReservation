package chooeat.account.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;

import chooeat.account.dao.AccountDao;
import chooeat.account.model.vo.AccountVo;
@Repository
@Import(DataSourceAutoConfiguration.class)
public class AccountDaoImpl implements AccountDao {
	@Autowired
	private DataSource dataSource;



//-------------------------------------------------查全部----------------------------------------------------------------
	@Override
	public List<AccountVo> selectAll() throws SQLException {
		String sql = "select * from account; ";
		try (Connection conn = dataSource.getConnection();

				PreparedStatement stmt = conn.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery()) {
			List<AccountVo> list = new ArrayList<AccountVo>();
			while (rs.next()) {
				AccountVo accountVO = new AccountVo();
				accountVO.setAcc_id(rs.getInt("acc_id"));
				accountVO.setAcc_acc(rs.getString("acc_acc"));
				accountVO.setAcc_pass(rs.getString("acc_pass"));
				accountVO.setAcc_name(rs.getString("acc_name"));
				accountVO.setAcc_nickname(rs.getString("acc_nickname"));
				accountVO.setAcc_phone(rs.getString("acc_phone"));
				accountVO.setAcc_mail(rs.getString("acc_mail"));
				accountVO.setAcc_add1(rs.getString("acc_add1"));
				accountVO.setAcc_add2(rs.getString("acc_add2"));
				accountVO.setAcc_add3(rs.getString("acc_add3"));
				accountVO.setAcc_birth(rs.getDate("acc_birth"));
				accountVO.setAcc_gender(rs.getInt("acc_gender"));
				accountVO.setAcc_pic(rs.getBytes("acc_pic"));
				accountVO.setAcc_text(rs.getString("acc_text"));
				accountVO.setAcc_timestamp(rs.getTimestamp("acc_timestamp"));
				accountVO.setAcc_state(rs.getInt("acc_state"));
				accountVO.setOnline_status(rs.getInt("online_status"));
				list.add(accountVO);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// --------------------------------------------登入---------------------------------------------------------------------
	@Override
	public AccountVo login(String acc, String pass) {
		String sql = "SELECT * FROM account WHERE acc_acc = ? and acc_pass = ?";

		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setString(1, acc);
			stmt.setString(2, pass);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					AccountVo accountVO = new AccountVo();
					accountVO.setAcc_id(rs.getInt("acc_id"));
					accountVO.setAcc_acc(rs.getString("acc_acc"));
					accountVO.setAcc_pass(rs.getString("acc_pass"));
					accountVO.setAcc_name(rs.getString("acc_name"));
					accountVO.setAcc_nickname(rs.getString("acc_nickname"));
					accountVO.setAcc_phone(rs.getString("acc_phone"));
					accountVO.setAcc_mail(rs.getString("acc_mail"));
					accountVO.setAcc_add1(rs.getString("acc_add1"));
					accountVO.setAcc_birth(rs.getDate("acc_birth"));
					accountVO.setAcc_gender(rs.getInt("acc_gender"));
					accountVO.setAcc_pic(rs.getBytes("acc_pic"));
					accountVO.setAcc_text(rs.getString("acc_text"));
					return accountVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ------------------------------------------註冊-----------------------------------------------------------------------
	@Override
	public List<AccountVo> register(List<AccountVo> accountList) {
		String sql = "INSERT INTO account (acc_acc, acc_pass, acc_name, acc_nickname, acc_phone, acc_mail, acc_add1, acc_add2, acc_add3, acc_birth, acc_gender,acc_state,online_status) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		List<AccountVo> result = new ArrayList<>(); // 创建结果列表

		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			for (AccountVo account : accountList) {
				int idx = 1;
				stmt.setString(idx++, account.getAcc_acc());
				stmt.setString(idx++, account.getAcc_pass());
				stmt.setString(idx++, account.getAcc_name());
				stmt.setString(idx++, account.getAcc_nickname());
				stmt.setString(idx++, account.getAcc_phone());
				stmt.setString(idx++, account.getAcc_mail());
				stmt.setString(idx++, account.getAcc_add1());
				stmt.setString(idx++, account.getAcc_add2());
				stmt.setString(idx++, account.getAcc_add3());
				stmt.setDate(idx++, new java.sql.Date(account.getAcc_birth().getTime()));
				stmt.setInt(idx++, account.getAcc_gender());
				stmt.setInt(idx++, account.getAcc_state());
				stmt.setInt(idx++, account.getOnline_status());
				System.out.println(stmt);
				int affectedRows = stmt.executeUpdate();
				if (affectedRows > 0) {
					// 如果需要返回新增的帐户列表，根据数据库结果进行设置
					ResultSet generatedKeys = stmt.getGeneratedKeys();
					if (generatedKeys.next()) {
						AccountVo createdAccount = new AccountVo();
						createdAccount.setAcc_id(generatedKeys.getInt(1));
						// 其他屬性的設定，根據需要進行相應的設定
						result.add(createdAccount);
					}
				}
				System.out.println("帳號：" + account.getAcc_acc());

				// 如果需要返回新增的帐户列表，根据数据库结果进行设置

				return result;
			}
		
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	// ------------------------------------------------更新-----------------------------------------------------------------

	@Override
	public List<AccountVo> update(List<AccountVo> accountList) {
		String sql = "UPDATE account SET acc_name = ?, acc_nickname = ?, acc_phone = ?, acc_mail = ?, acc_add1 = ?, acc_birth = ?, acc_gender = ?,acc_text=?,acc_pic=? WHERE acc_id = ?";

		List<AccountVo> result = new ArrayList<>(); // 创建结果列表

		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			for (AccountVo account : accountList) {
				int idx = 1;
				stmt.setString(idx++, account.getAcc_name());
				stmt.setString(idx++, account.getAcc_nickname());
				stmt.setString(idx++, account.getAcc_phone());
				stmt.setString(idx++, account.getAcc_mail());
				stmt.setString(idx++, account.getAcc_add1());
				stmt.setDate(idx++, new java.sql.Date(account.getAcc_birth().getTime()));
				stmt.setInt(idx++, account.getAcc_gender());
				stmt.setString(idx++, account.getAcc_text());
				stmt.setBytes(idx++, account.getAcc_pic());
				stmt.setInt(idx++, account.getAcc_id()); // 更新條件，根據實際需求設定
				System.out.println(stmt);

				int affectedRows = stmt.executeUpdate();
				if (affectedRows > 0) {
					result.add(account); // 將更新成功的帳戶加入結果列表
				}
			}
			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

//---------------------------------------------顯示資料--------------------------------------------------------------------
//	@Override
//	public List<AccountVo> printdata(List<AccountVo> accountList) {
//		String sql = "SELECT * FROM account WHERE acc_id=?";
//			AccountVo accountVO = new AccountVo();
//		try {
//			Connection conn = dataSource.getConnection();
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			 stmt.setInt(1, accountList.get(0).getAcc_id());
//			ResultSet rs = stmt.executeQuery();
//		
//			while (rs.next()) {
//				
//				accountVO.setAcc_id(rs.getInt("acc_id"));
//				accountVO.setAcc_acc(rs.getString("acc_acc"));
//				accountVO.setAcc_pass(rs.getString("acc_pass"));
//				accountVO.setAcc_name(rs.getString("acc_name"));
//				accountVO.setAcc_nickname(rs.getString("acc_nickname"));
//				accountVO.setAcc_phone(rs.getString("acc_phone"));
//				accountVO.setAcc_mail(rs.getString("acc_mail"));
//				accountVO.setAcc_add1(rs.getString("acc_add1"));
//				accountVO.setAcc_add2(rs.getString("acc_add2"));
//				accountVO.setAcc_add3(rs.getString("acc_add3"));
//				accountVO.setAcc_birth(rs.getDate("acc_birth"));
//				accountVO.setAcc_gender(rs.getInt("acc_gender"));
//				accountVO.setAcc_pic(rs.getBytes("acc_pic"));
//				accountVO.setAcc_text(rs.getString("acc_text"));
//				accountVO.setAcc_timestamp(rs.getTimestamp("acc_timestamp"));
//				accountVO.setAcc_state(rs.getInt("acc_state"));
//				accountVO.setOnline_status(rs.getInt("online_status"));
//				return (List<AccountVo>) accountVO;
//				
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		return (List<AccountVo>) accountVO;
//	}
//
//

//---------------------------------------------查詢信箱--------------------------------------------------------------------
	@Override
	public int updatepass(String forgetemail) {
		String sql = "SELECT * FROM account WHERE acc_mail=?"; 	
		int result = 0;  //初始值
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, forgetemail);		
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result = 1;				  //有資料
			}			
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();  
			return 0;
		}
		return result;
	}
 //---------------------------------------------用信箱更改密碼--------------------------------------------------------------------

	@Override
	public int randomPass(int a ,String forgetemail) {
	
		String sql = "UPDATE account SET acc_pass =?\r\n"
				+ "WHERE acc_mail =?";
		int result = 0;
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a);	
			stmt.setString(2, forgetemail);
			
			int affectedRows = stmt.executeUpdate();
			
			if(affectedRows>0) {
				result = 1;
				
			};
		
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return result;
	}
//---------------------------------------------用id更改密碼--------------------------------------------------------------------

	@Override
	public String changpass(int id, String password2 ) {
		String sql = "UPDATE account SET acc_pass = ?WHERE acc_id = ?";
		int result = 0;
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, password2);	
			stmt.setInt(2, id);
			
			int affectedRows = stmt.executeUpdate();
			
			if(affectedRows>0) {
				result = 1;
				
			};
			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
//---------------------------------------------查詢名稱,信箱--------------------------------------------------------------------

	@Override
	public int cheak(List<String> usernames, List<String> mails) {
	    String sql = "SELECT COUNT(*) FROM account WHERE acc_acc=? OR acc_mail=?";
	    int result = 0;
	    try {
	        Connection conn = dataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        for (int i = 0; i < usernames.size(); i++) {
	            stmt.setString(1, usernames.get(i));
	            stmt.setString(2, mails.get(i));
	            
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int count = rs.getInt(1);
	                if (count > 0) {
	                    result = 1;
	                    break;
	                }
	            }
	            rs.close();
	        }
	        
	        stmt.close();
	        conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	    return result;
	}

	
//	

	
}
