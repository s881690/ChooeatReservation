package chooeat.admin.web.prod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.prod.dao.ProdRepository;
import chooeat.admin.web.prod.pojo.ProdVO;
import chooeat.admin.web.prod.service.ProdService;

@Service
public class AdminProdServiceImpl implements ProdService {

	@Autowired
	private ProdRepository prodRepository;

	@Override
	public List<ProdVO> selectAll() {
		List<ProdVO> prodList = prodRepository.findAll();
		return prodList;
	}

	@Override
	public ProdVO findByProdId(Integer prodId) {
		return prodRepository.findByProdId(prodId);
	}

	@Override
	public List<ProdVO> searchBySomethingId(Integer searchType, Integer id) {
		if(searchType == 1) {
			String prodId = "%" + String.valueOf(id) + "%";
			return prodRepository.findByProdIdLike(prodId);
		} else if (searchType == 3) {
			String restaurantId = "%" + String.valueOf(id) + "%";
			return prodRepository.findByRestaurantIdLike(restaurantId);	
		} else {
			return null;
		}
	}

	@Override
	public List<ProdVO> searchBySomethingName(Integer searchType, String name) {
		if(searchType == 2) {
			return prodRepository.findByProdNameLike(name);
		} else if (searchType == 4) {
			return prodRepository.findByRestaurantNameLike(name);
		} else {
			return null;
		}
	}
}
