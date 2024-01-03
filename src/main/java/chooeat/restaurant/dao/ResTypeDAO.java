package chooeat.restaurant.dao;

import java.util.List;

import chooeat.restaurant.model.vo.RestaurantVO;

public interface ResTypeDAO {
	List<RestaurantVO> searchrestaurantbytag(String tag);
}
