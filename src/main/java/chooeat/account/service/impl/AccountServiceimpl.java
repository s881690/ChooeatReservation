package chooeat.account.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.account.dao.AccountDao;
import chooeat.account.model.vo.AccountVo;
import chooeat.account.service.AccountService;
@Service
public class AccountServiceimpl implements AccountService {
	@Autowired
	private AccountDao dao;

	

	@Override
	public List<AccountVo> selectAll() {

		return null;
	}

	@Override
	public AccountVo login(String acc, String pass) {
		AccountVo member = dao.login(acc, pass);
		return member;
	}

	@Override
	public int register(List<AccountVo> accountList) {
		// TODO Auto-generated method stub
		List<String> usernames = new ArrayList<>();
		List<String> mails = new ArrayList<>();
		for (AccountVo account : accountList) {
			String username = account.getAcc_acc();
			String mail = account.getAcc_mail();
			usernames.add(username);
			mails.add(mail);

		}
		int a = dao.cheak(usernames, mails);
		if (a == 1) {
			System.out.println("資料重複");
			return 1;

		} else {

			dao.register(accountList);
			return 0;
		}

	}

	@Override
	public List<AccountVo> update(List<AccountVo> accountList) {
		// TODO Auto-generated method stub
		System.out.println(accountList);
		dao.update(accountList);
		return null;
	}

	@Override
	public int[] updatepass(String forgetemail) {
		int result = dao.updatepass(forgetemail);

		int[] values = new int[2];
		values[0] = 0; // 初始值
		values[1] = 0; // 初始值

		if (result == 1) {
			Random random = new Random();
			int a = random.nextInt(1000);
			values[0] = dao.randomPass(a, forgetemail);
			values[1] = a;
			return values;

		} else {

			return values;
		}

	}

	@Override
	public String changpass(int id, String password2, String sqlpassword, String password) {
		String a = "0";
		if (password != "" && password.equals(sqlpassword)) {

			dao.changpass(id, password2);
			a = "1";

		} else {
			System.out.println("與原密碼不同");
		}

		return a;
	}

//	@Override
//	public String changpass(String forgetemail) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<AccountVo> printdata(List<AccountVo> accountList) {
//		// TODO Auto-generated method stub
//		return dao.printdata(accountList);
//		
//	//	JSONObjec json=new JSONObject();
//		
//		 
//	}

}