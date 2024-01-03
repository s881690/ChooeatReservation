package chooeat.prod.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.prod.model.vo.Prod;
import chooeat.prod.service.ProdService;
@Service
public class ProdServiceImpl implements ProdService {
	@Autowired
	private DataSource dataSource;
	public List<Prod> selectAll() {
		return null;
	}

	@Override
	public List<Prod> selectByProdName() {
		return null;
	}

	@Override
	public int insert(Prod prod) {
		return 0;
	}


	public List<Prod> getSortedProducts(List<Prod> prod, String sortParam) {
	    // 依 sortParam 執行相應的商品排序邏輯
	    // 依評分排序
	    if ("star".equals(sortParam)) {
	        sortProductsByStar(prod);
	    }
	    // 依價格從高到低排序
	    else if ("pricefromhigh".equals(sortParam)) {
	        sortProductsByPrice(prod, true);
	    }
	    // 依價格從低到高排序
	    else if ("pricefromlow".equals(sortParam)) {
	        sortProductsByPrice(prod, false);
	    }
	    return prod;
	}
	public void sortProductsByStar(List<Prod> prod) {
	    // 依星星排序
	    Collections.sort(prod, Comparator.comparing(Prod::getProdCommentScore).reversed());
	}


	public void sortProductsByPrice(List<Prod> prod, boolean sortByHighToLow) {
		// 依價格排序
		if (sortByHighToLow) {
			Collections.sort(prod, Comparator.comparing(Prod::getProdPrice).reversed());
		} else {
			Collections.sort(prod, Comparator.comparing(Prod::getProdPrice));
		}
	}
	
	
	public List<Prod> getByCategory(String category) {
	    String sql = "SELECT prod_id,"
	            + "r.restaurant_id,"
	            + "prod_name,"
	            + "prod_text,"
	            + "prod_userguide,"
	            + "prod_price,prod_qty,"
	            + "prod_state,prod_pic,"
	            + "prod_comment_number,"
	            + "prod_comment_score,"
	            + "res_acc,"
	            + "res_name,"
	            + "res_add,"
	            + "GROUP_CONCAT(res_type_name SEPARATOR ' / ') AS category_names "
	            + "FROM "
	            + "((chooeat.prod p JOIN chooeat.RESTAURANT r ON p.RESTAURANT_ID = r.RESTAURANT_ID) "
	            + "JOIN chooeat.RES_TYPE_DETAIL rtd ON p.RESTAURANT_ID = rtd.RESTAURANT_ID) "
	            + "JOIN chooeat.RES_TYPE rt ON rtd.RES_TYPE_ID = rt.RES_TYPE_ID "
	            + "GROUP BY p.prod_id, r.RESTAURANT_ID, res_name "
	            + "HAVING category_names LIKE ?";

	    try (
	        Connection conn = dataSource.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	    ) {
	        pstmt.setString(1, "%" + category + "%");
	        ResultSet rs = pstmt.executeQuery();
	        List<Prod> list = new ArrayList<Prod>();
	        while (rs.next()) {
	            Prod prod = new Prod();
	            prod.setProdId(rs.getInt("PROD_ID"));
	            prod.setRestaurantId(rs.getInt("RESTAURANT_ID"));
	            prod.setProdName(rs.getString("PROD_NAME"));
	            prod.setProdText(rs.getString("PROD_TEXT"));
	            prod.setProdUserguide(rs.getString("PROD_USERGUIDE"));
	            prod.setProdPrice(rs.getInt("PROD_PRICE"));
	            prod.setProdQty(rs.getInt("PROD_QTY"));
	            prod.setProdState(rs.getInt("PROD_STATE"));
	            prod.setProdCommentNumber(rs.getInt("PROD_COMMENT_NUMBER"));
	            prod.setProdCommentScore(rs.getInt("PROD_COMMENT_SCORE"));
	            prod.setResName(rs.getString("RES_NAME"));
	            prod.setResAdd(rs.getString("RES_ADD"));
	            prod.setResType(rs.getString("category_names"));
				byte[] photoBytes = rs.getBytes("PROD_PIC");
				  if (photoBytes != null && photoBytes.length > 0) {
		                byte[] photoWrapper = new byte[photoBytes.length];
		                for (int i = 0; i < photoBytes.length; i++) {
		                    photoWrapper[i] = photoBytes[i];
		                }
		                prod.setProdPic(photoWrapper);
		            } else {
		            	prod.setProdPic(null);
		            }	
	            list.add(prod);
	        }
	        return list;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
}
