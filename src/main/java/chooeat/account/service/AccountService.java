package chooeat.account.service;

import java.util.List;

import chooeat.account.model.vo.AccountVo;

public interface AccountService {

    public List<AccountVo> selectAll();
    public AccountVo login(String acc,String pass);
    public int register(List<AccountVo> accountList); 
    public List<AccountVo> update(List<AccountVo> accountList);
    public int[] updatepass(String forgetemail);

	public String changpass(int id,String password2,String sqlpassword,String password );
	

    
}