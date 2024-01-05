package chooeat.account.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import chooeat.account.dao.AccountDao;
import chooeat.account.model.vo.AccountVo;
@Repository
public class AccountDaoImpl implements AccountDao {

	private static final class AccountRowMapper implements RowMapper<AccountVo> {
		@Override
		public AccountVo mapRow(ResultSet rs, int rowNum) throws SQLException {
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
			return accountVO;
		}
	}



//-------------------------------------------------查全部----------------------------------------------------------------
@Autowired
private JdbcTemplate jdbcTemplate;

	@Override
	public List<AccountVo> selectAll() {
		String sql = "SELECT * FROM account";
		return jdbcTemplate.query(sql, new AccountRowMapper());
	}



	// --------------------------------------------登入---------------------------------------------------------------------
	@Override
	public AccountVo login(String acc, String pass) {
		String sql = "SELECT * FROM account WHERE acc_acc = ? AND acc_pass = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[]{acc, pass}, new AccountRowMapper());
		} catch (Exception e) {
			// 在這裡處理例外，例如用戶名或密碼不正確的情況
			e.printStackTrace();
			return null;
		}
	}

	// ------------------------------------------註冊-----------------------------------------------------------------------
	@Override
	public List<AccountVo> register(List<AccountVo> accountList) {
		String sql = "INSERT INTO account (acc_acc, acc_pass, acc_name, acc_nickname, acc_phone, acc_mail, acc_add1, acc_add2, acc_add3, acc_birth, acc_gender, acc_state, online_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		for (AccountVo account : accountList) {
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, account.getAcc_acc());
				ps.setString(2, account.getAcc_pass());
				ps.setString(3, account.getAcc_name());
				ps.setString(4, account.getAcc_nickname());
				ps.setString(5, account.getAcc_phone());
				ps.setString(6, account.getAcc_mail());
				ps.setString(7, account.getAcc_add1());
				ps.setString(8, account.getAcc_add2());
				ps.setString(9, account.getAcc_add3());
				ps.setDate(10, new java.sql.Date(account.getAcc_birth().getTime()));
				ps.setInt(11, account.getAcc_gender());
				ps.setInt(12, account.getAcc_state());
				ps.setInt(13, account.getOnline_status());
				return ps;
			}, keyHolder);

			// 將生成的主鍵設置到 account 對象
			account.setAcc_id(keyHolder.getKey().intValue());
		}

		// 返回帶有生成的主鍵的 account 列表
		return accountList;
	}
	// ------------------------------------------------更新-----------------------------------------------------------------

	@Override
	public List<AccountVo> update(List<AccountVo> accountList) {
		String sql = "UPDATE account SET acc_name = ?, acc_nickname = ?, acc_phone = ?, acc_mail = ?, acc_add1 = ?, acc_birth = ?, acc_gender = ?, acc_text = ?, acc_pic = ? WHERE acc_id = ?";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				AccountVo account = accountList.get(i);
				ps.setString(1, account.getAcc_name());
				ps.setString(2, account.getAcc_nickname());
				ps.setString(3, account.getAcc_phone());
				ps.setString(4, account.getAcc_mail());
				ps.setString(5, account.getAcc_add1());
				ps.setDate(6, new java.sql.Date(account.getAcc_birth().getTime()));
				ps.setInt(7, account.getAcc_gender());
				ps.setString(8, account.getAcc_text());
				ps.setBytes(9, account.getAcc_pic());
				ps.setInt(10, account.getAcc_id());
			}

			@Override
			public int getBatchSize() {
				return accountList.size();
			}
		});

		// 回傳更新的帳戶列表
		return accountList;
	}

//---------------------------------------------查詢信箱--------------------------------------------------------------------
@Override
public int updatepass(String forgetemail) {
	String sql = "SELECT COUNT(*) FROM account WHERE acc_mail = ?";
	try {
		int count = jdbcTemplate.queryForObject(sql, new Object[]{forgetemail}, Integer.class);
		return count > 0 ? 1 : 0;
	} catch (Exception e) {
		e.printStackTrace();
		return 0;
	}
}
 //---------------------------------------------用信箱更改密碼--------------------------------------------------------------------

	@Override
	public int randomPass(int a, String forgetemail) {
		String sql = "UPDATE account SET acc_pass = ? WHERE acc_mail = ?";
		try {
			int affectedRows = jdbcTemplate.update(sql, a, forgetemail);
			return affectedRows > 0 ? 1 : 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
//---------------------------------------------用id更改密碼--------------------------------------------------------------------

	@Override
	public String changpass(int id, String password2) {
		String sql = "UPDATE account SET acc_pass = ? WHERE acc_id = ?";
		try {
			int affectedRows = jdbcTemplate.update(sql, password2, id);
			return affectedRows > 0 ? "1" : "0";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
//---------------------------------------------查詢名稱,信箱--------------------------------------------------------------------

	@Override
	public int cheak(List<String> usernames, List<String> mails) {
		String sql = "SELECT COUNT(*) FROM account WHERE acc_acc = ? OR acc_mail = ?";
		try {
			for (int i = 0; i < usernames.size(); i++) {
				int count = jdbcTemplate.queryForObject(sql, Integer.class, usernames.get(i), mails.get(i));
				if (count > 0) {
					return 1;
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
}
