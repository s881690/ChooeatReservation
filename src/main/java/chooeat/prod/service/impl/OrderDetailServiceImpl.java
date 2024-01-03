package chooeat.prod.service.impl;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.prod.model.vo.OrderDetail;
import chooeat.prod.model.vo.Prod;
@Service
public class OrderDetailServiceImpl {
	@Autowired
	private DataSource dataSource;

	private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int CODE_LENGTH = 16;
	private static final SecureRandom random = new SecureRandom();
	
	public List<OrderDetail> selectAll() {
		return null;
	}

	public static void main(String[] args) {
		String randomCode = generateRandomCode();
		System.out.println("Random Code: " + randomCode);
	}

	public static String generateRandomCode() {
		StringBuilder sb = new StringBuilder(CODE_LENGTH);

		for (int i = 0; i < CODE_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}
	
	
	public List<OrderDetail> selectByProdId(int prodId) {
	    String sql = "SELECT OD.*, O.ACC_ID, A.ACC_NAME " +
	                 "FROM ORDER_DETAIL OD " +
	                 "JOIN chooeat.ORDER O ON OD.ORDER_ID = O.ORDER_ID " +
	                 "JOIN ACCOUNT A ON O.ACC_ID = A.ACC_ID " +
	                 "WHERE OD.PROD_ID = ?";
	    List<OrderDetail> orderDetails = new ArrayList<>();

	    try (
	        Connection conn = dataSource.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	    ) {
	        pstmt.setInt(1, prodId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                OrderDetail orderDetail = new OrderDetail();
	                orderDetail.setOrderDetailId(rs.getInt("ORDER_DETAIL_ID"));
	                orderDetail.setOrderId(rs.getInt("ORDER_ID"));
	                orderDetail.setProdId(rs.getInt("PROD_ID"));

	                orderDetail.setProdCommentScore(rs.getInt("PROD_COMMENT_SCORE"));
	                orderDetail.setProdCommentText(rs.getString("PROD_COMMENT_TEXT"));
	                orderDetail.setProdCommentTimestamp(rs.getTimestamp("PROD_COMMENT_TIMESTAMP"));
	                // Get ACC_NAME from the joined ACCOUNT table
	                String accName = rs.getString("ACC_NAME");
	                orderDetail.setAccName(accName);

	                orderDetails.add(orderDetail);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orderDetails;
	}

	
	
	
	
	
	
}
