package chooeat.prod.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import chooeat.prod.model.vo.OrderDetail;
import chooeat.prod.service.ProdCommentService;

@Service
public class ProdCommentServiceImpl  implements ProdCommentService{

	@Override
	public List<OrderDetail> getSortedProducts(List<OrderDetail> orderDetail, String sortParam) {
	    // 依評分排序
	    if ("star".equals(sortParam)) {
	        sortProductsByStar(orderDetail);
	    }
	    return orderDetail;
	
	}
	public void sortProductsByStar(List<OrderDetail> orderDetail) {
	    // 依星星排序
	    Collections.sort(orderDetail, Comparator.comparing(OrderDetail:: getProdCommentScore).reversed());
	}

}
