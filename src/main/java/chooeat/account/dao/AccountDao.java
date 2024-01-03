package chooeat.account.dao;

import java.sql.SQLException;
import java.util.List;

import chooeat.account.model.vo.AccountVo;


public interface AccountDao {
	
	List<AccountVo>selectAll() throws SQLException;
	AccountVo login(String acc, String pass);
	List<AccountVo> register(List<AccountVo> accountList);
	List<AccountVo> update(List<AccountVo> accountList);
//	List<AccountVo> printdata(List<AccountVo> accountList);
	int updatepass(String forgetemail );
	int randomPass(int a ,String forgetemail);
	String changpass(int id ,String password2 );
	int cheak(List<String> usernames,List<String> mails);
}
