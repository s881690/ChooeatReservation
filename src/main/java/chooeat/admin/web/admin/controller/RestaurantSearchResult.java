package chooeat.admin.web.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.web.restaurant.pojo.AdminRestaurantVO;
import chooeat.admin.web.restaurant.service.RestaurantService;

@RestController
@RequestMapping("/restaurantSearch")
public class RestaurantSearchResult {
	
	@Autowired
	private RestaurantService SERVICE;
	
	@GetMapping("/searchResult")
	public AdminRestaurantVO findRes(Integer restaurantId) {
		return SERVICE.findByResId(restaurantId);
	}

}
