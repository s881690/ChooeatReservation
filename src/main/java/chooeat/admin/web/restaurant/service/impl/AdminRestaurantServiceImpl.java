package chooeat.admin.web.restaurant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.restaurant.dao.AdminRestaurantRepository;
import chooeat.admin.web.restaurant.dao.RestaurantDAO;
import chooeat.admin.web.restaurant.pojo.AdminRestaurantPOJO;
import chooeat.admin.web.restaurant.pojo.AdminRestaurantVO;
import chooeat.admin.web.restaurant.service.RestaurantService;

@Service
public class AdminRestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantDAO dao;
	
	@Autowired
	private AdminRestaurantRepository restaurantRepository;

	@Override
	public AdminRestaurantVO findByResId(Integer resId) {
		return restaurantRepository.findByRestaurantId(resId);
	}

	@Override
	public AdminRestaurantVO edit(Integer resId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminRestaurantPOJO> findAll() {
		return dao.selectAllPOJO();
	}

	@Override
	public List<AdminRestaurantPOJO> searchBySomething(String searchType, String search) {
		String searchCondition = "%" + search + "%";
		if("1".equals(searchType)) {
			return dao.selectAllPOJOByResId(searchCondition);
		} else {
			return dao.selectAllPOJOByResName(searchCondition);			
		}
	}

}
