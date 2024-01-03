package chooeat.admin.web.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.admin.dao.AdminDAO;
import chooeat.admin.web.admin.dao.AdminRepository;
import chooeat.admin.web.admin.pojo.AdminVO;
import chooeat.admin.web.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO dao;
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public AdminVO register(AdminVO admin) {
		if (admin.getAdminAcc() == null) {
			admin.setMessage("帳號未輸入");
			admin.setSuccessful(false);
			return admin;
		}
		
		if (admin.getAdminName() == null) {
			admin.setMessage("名稱未輸入");
			admin.setSuccessful(false);
			return admin;
		}
		
		if (admin.getAdminPass() == null) {
			admin.setMessage("密碼未輸入");
			admin.setSuccessful(false);
			return admin;
		}
		
		if (dao.selectByAdminAcc(admin.getAdminAcc()) != null) {
			admin.setMessage("此帳號名稱已被註冊，請更換帳號");
			admin.setSuccessful(false);
			return admin;
		}
		
		if (dao.selectByAdminName(admin.getAdminName()) != null) {
			admin.setMessage("此管理員名稱已被註冊，請更換管理員名稱");
			admin.setSuccessful(false);
			return admin;
		}
		
		final int resultCount = dao.insert(admin);
		
		if (resultCount < 1) {
			admin.setMessage("註冊錯誤，請聯絡管理員!");
			admin.setSuccessful(false);
			return admin;
		}
		
		admin.setMessage("註冊成功");
		admin.setSuccessful(true);
		return admin;
	}
	
	@Override
	public AdminVO editAdmin(AdminVO admin) {
		
		if (admin.getAdminName() == null) {
			admin.setMessage("名稱未輸入");
			admin.setSuccessful(false);
			return admin;
		}
		
		if (admin.getAdminPass() == null) {
			admin.setMessage("密碼未輸入");
			admin.setSuccessful(false);
			return admin;
		}
		
		if (dao.selectByAdminName(admin.getAdminName()) != null && dao.selectByAdminName(admin.getAdminName()).getAdminId() != admin.getAdminId()) {
			admin.setMessage("此管理員名稱已被註冊，請更換管理員名稱");
			admin.setSuccessful(false);
			return admin;
		}
		
		final int resultCount = dao.update(admin);
		admin.setSuccessful(resultCount > 0);
		admin.setMessage(resultCount > 0 ? "更新成功!" : "更新失敗");
		
		return admin;
		
//		AdminVO newAdmin = adminRepository.save(admin);
//		
//		if (newAdmin == null) {
//			admin.setMessage("註冊錯誤，請聯絡管理員!");
//			admin.setSuccessful(false);
//			return admin;
//		}
//		
//		newAdmin.setMessage("更新成功!");
//		newAdmin.setSuccessful(true);
//		return newAdmin;
	}

	@Override
	public AdminVO login(AdminVO adminVO) {
		final String acc = adminVO.getAdminAcc();
		final String pwd = adminVO.getAdminPass();
		
		if (acc == null || acc.length() == 0) {
			adminVO.setMessage("帳號未輸入");
			adminVO.setSuccessful(false);
			return adminVO;
		}
		
		if (pwd == null || pwd.length() == 0) {
			adminVO.setMessage("密碼未輸入");
			adminVO.setSuccessful(false);
			return adminVO;
		}
		
		adminVO = dao.selectForLogin(acc, pwd);
		if (adminVO == null) {
			adminVO = new AdminVO();
			adminVO.setMessage("帳號或密碼錯誤");
			adminVO.setSuccessful(false);
			return adminVO;
		}
		
		adminVO.setMessage("登入成功");
		adminVO.setSuccessful(true);
		return adminVO;
	}

	@Override
	public AdminVO edit(Integer adminId, Integer adminPermission) {
		AdminVO admin = dao.selectById(adminId);
		
		admin.setAdminAcc(admin.getAdminAcc());
		admin.setAdminName(admin.getAdminName());
		admin.setAdminPass(admin.getAdminPass());
		admin.setAdminPermission(adminPermission);
		
		final int resultCount = dao.update(admin);
		admin.setSuccessful(resultCount > 0);
		admin.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		
		return admin;
	}

	@Override
	public List<AdminVO> findAll() {
		return dao.selectAll();
	}

	@Override
	public List<AdminVO> searchBySomething(String searchType, String search) {	
		String searchCondition = "%" + search + "%";
		if("1".equals(searchType)) {
			return dao.selectAllByAdminName(searchCondition);
		} else {
			return dao.selectAllByAdminAcc(searchCondition);			
		}
	}

	@Override
	public boolean remove(Integer adminId) {
		adminRepository.deleteById(adminId);
		return true;
	}
	
}
