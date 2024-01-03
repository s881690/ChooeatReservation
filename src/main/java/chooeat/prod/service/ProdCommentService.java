package chooeat.prod.service;

import java.util.List;

import org.springframework.stereotype.Service;

import chooeat.prod.model.vo.OrderDetail;
import chooeat.prod.model.vo.Prod;

public interface ProdCommentService {

	 public List<OrderDetail> getSortedProducts(List<OrderDetail> orderDetail, String sortParam);
	
}
