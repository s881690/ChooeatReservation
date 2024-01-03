package chooeat.admin.web.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.admin.web.admin.pojo.AdminVO;

public interface AdminRepository extends JpaRepository<AdminVO, Integer>{

	AdminVO findByAdminId (Integer adminId); 
}
