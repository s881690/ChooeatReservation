package chooeat.admin.web.restaurant.dao;

import java.util.List;

import chooeat.admin.web.restaurant.pojo.AdminRestaurantPOJO;
import chooeat.admin.web.restaurant.pojo.AdminRestaurantVO;

public interface RestaurantDAO {
	
	AdminRestaurantVO findByResId(String resId);

	int register(List<String> values);	

	int updateByRestaurantId(AdminRestaurantVO RestaurantVO);
	
	List<AdminRestaurantVO> searchRestaurants(String searchString);
	
	List<AdminRestaurantVO> login(String account,String password);
	
	List<Object> findfollow(String resAcc);
	
	List<Object> findrestype(String resAcc);
	
	List<Object> findprod(String resAcc);
	
	List<Object> findcomment(String resAcc);
	
	List<Object> findmyself(String resAcc);
	
	List<AdminRestaurantVO> selectAll (); 
	
	List<AdminRestaurantPOJO> selectAllPOJO();
	
	List<AdminRestaurantPOJO> selectAllPOJOByResId (String resId);
	
	List<AdminRestaurantPOJO> selectAllPOJOByResName (String resName);
}
