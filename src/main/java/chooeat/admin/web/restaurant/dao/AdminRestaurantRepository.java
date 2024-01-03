package chooeat.admin.web.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.admin.web.restaurant.pojo.AdminRestaurantVO;

public interface AdminRestaurantRepository extends JpaRepository<AdminRestaurantVO, Integer>{

	AdminRestaurantVO findByRestaurantId(Integer resId);
}
