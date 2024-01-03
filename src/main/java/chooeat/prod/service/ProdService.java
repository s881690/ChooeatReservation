package chooeat.prod.service;

import java.util.List;

import org.springframework.stereotype.Service;

import chooeat.prod.model.vo.Prod;

public interface ProdService {
	 public int insert(Prod prod);
	
	 public List<Prod> selectAll();
	 
	 public List<Prod> selectByProdName();
	 
	 public List<Prod> getSortedProducts(List<Prod> prod, String sortParam);
	
}
