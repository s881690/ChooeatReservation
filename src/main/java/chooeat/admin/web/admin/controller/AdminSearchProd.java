package chooeat.admin.web.admin.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.web.prod.pojo.ProdVO;
import chooeat.admin.web.prod.service.ProdService;

@RestController
@RequestMapping("/adminSearchProd")
public class AdminSearchProd {

	@Autowired
	private ProdService SERVICE;
	
	@GetMapping("/selectAll")
	public List<ProdVO> findList(Integer searchType, String search){
		
		if(searchType == 0) {
			return SERVICE.selectAll();
		} else if (searchType == 1 || searchType == 3){
			try {
	            int id = Integer.parseInt(search);
	            return SERVICE.searchBySomethingId(searchType, id);
	        } catch (NumberFormatException e) {
	            return Collections.emptyList();
	        }
		} else if (searchType == 2 || searchType == 4) {
			return SERVICE.searchBySomethingName(searchType, search);
		} else {
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/findProd")
	public ProdVO findProd(Integer prodId) {
		return SERVICE.findByProdId(prodId);
	}
}
