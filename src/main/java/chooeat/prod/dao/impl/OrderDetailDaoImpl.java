package chooeat.prod.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;

import chooeat.prod.dao.OrderDetailDao;
import chooeat.prod.model.vo.OrderDetail;
@Repository
@Import(DataSourceAutoConfiguration.class)
public class OrderDetailDaoImpl implements OrderDetailDao {
	@Autowired
	private DataSource dataSource;

	@Override
	public int insert(OrderDetail orderDetail) {
		 String getMaxOrderDetailIdQuery = "SELECT MAX(ORDER_DETAIL_ID) FROM `ORDER_DETAIL`";
		String sql = "insert into ORDER_DETAIL values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 try (Connection conn = dataSource.getConnection(); 
		         PreparedStatement getMaxOrderDetailIdStmt = conn.prepareStatement(getMaxOrderDetailIdQuery);
		         ResultSet maxOrderDetailIdRs = getMaxOrderDetailIdStmt.executeQuery();
		         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
	       
			int maxOrderDetailId = 0;
	        if (maxOrderDetailIdRs.next()) {
	            maxOrderDetailId = maxOrderDetailIdRs.getInt(1);
	        }
	        int newOrderDetailId = maxOrderDetailId + 1;

	        pstmt.setInt(1, newOrderDetailId);
			pstmt.setInt(2, orderDetail.getOrderId());
			pstmt.setInt(3, orderDetail.getProdId());
			pstmt.setInt(4, orderDetail.getProdPrice());
			pstmt.setInt(5, orderDetail.getOrderProdQty());
			pstmt.setNull(6, java.sql.Types.INTEGER);
			pstmt.setNull(7, java.sql.Types.VARCHAR);
			pstmt.setNull(8, java.sql.Types.VARCHAR);
			pstmt.setNull(9, java.sql.Types.TIMESTAMP);
			pstmt.setNull(10, java.sql.Types.TIMESTAMP);
			pstmt.setNull(11, java.sql.Types.INTEGER);
//			pstmt.setInt(6, orderDetail.getProdCommentScore());
//			pstmt.setString(7, orderDetail.getProdCommentText());
//			pstmt.setString(8, orderDetail.getResProdReplyText());
//			pstmt.setTimestamp(9, orderDetail.getProdCommentTimestamp());
//			pstmt.setTimestamp(10, orderDetail.getResProdReplyTimestamp());
//			pstmt.setInt(11, orderDetail.getAccCouponId());
	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating order failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int generatedOrderDetailId = generatedKeys.getInt(1);
	                orderDetail.setOrderDetailId(generatedOrderDetailId);
	                return generatedOrderDetailId;
	            } else {
	                throw new SQLException("Creating order failed, no ID obtained.");
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteByorderDetailId(Integer orderDetailId) {
		String sql="delete from ORDER_DETAIL_ID where ORDER_DETAIL_ID = ?";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, orderDetailId);
			return pstmt.executeUpdate();
		}
		catch(Exception e){
					e.printStackTrace();
					}
		return -1;
	}

	@Override
	public int updateByorderDetailId(OrderDetail orderDetail) {
		String sql ="update ORDER_DETAIL "
				+"set"
				+"	ORDER_ID = ?,"
				+"	PROD_ID = ?,"
				+"	PROD_PRICE = ?,"
				+"	ORDER_PROD_QTY = ?,"
				+"	PROD_COMMENT_SCORE = ?,"
				+"	PROD_COMMENT_TEXT = ?,"
				+"	RES_PROD_REPLY_TEXT = ?,"
				+"	PROD_COMMENT_TIMESTAMP = ?,"
				+"	RES_PROD_REPLY_TIMESTAMP = ?,"
				+"  ACC_COUPON_ID = ? "
				+"where ORDER_DETAIL_ID = ?";
		try(
			Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, orderDetail.getOrderDetailId());
			pstmt.setInt(2, orderDetail.getOrderId());
			pstmt.setInt(3, orderDetail.getProdId());
			pstmt.setInt(4, orderDetail.getProdPrice());
			pstmt.setInt(5, orderDetail.getOrderProdQty());
			pstmt.setInt(6, orderDetail.getProdCommentScore());
			pstmt.setString(7, orderDetail.getProdCommentText());
			pstmt.setString(8, orderDetail.getResProdReplyText());
			pstmt.setTimestamp(9, orderDetail.getProdCommentTimestamp());
			pstmt.setTimestamp(10, orderDetail.getResProdReplyTimestamp());
			pstmt.setInt(11, orderDetail.getAccCouponId());
			return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public OrderDetail selectByorderDetailId(Integer orderDetailId) {
		String sql="select * from ORDER_DETAIL where ORDER_DETAIL_ID = ?";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, orderDetailId);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrderDetailId(rs.getInt("ORDER_DETAIL_ID"));
					orderDetail.setOrderId(rs.getInt("ORDER_ID"));
					orderDetail.setProdId(rs.getInt("PROD_ID"));
					orderDetail.setProdPrice(rs.getInt("PROD_PRICE"));
					orderDetail.setOrderProdQty(rs.getInt("ORDER_PROD_QTY"));
					orderDetail.setProdCommentScore(rs.getInt("PROD_COMMENT_SCORE"));
					orderDetail.setProdCommentText(rs.getString("PROD_COMMENT_TEXT"));
					orderDetail.setResProdReplyText(rs.getString("RES_PROD_REPLY_TEXT"));
					orderDetail.setProdCommentTimestamp(rs.getTimestamp("PROD_COMMENT_TIMESTAMP"));
					orderDetail.setResProdReplyTimestamp(rs.getTimestamp("RES_PROD_REPLY_TIMESTAMP"));
					orderDetail.setAccCouponId(rs.getInt("ACC_COUPON_ID"));
					return orderDetail;
				}
			}
		}
		catch(Exception e){
				e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderDetail> selectAll() {
		String sql="select * from ORDER_DETAIL";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		){
			List<OrderDetail> list = new ArrayList<OrderDetail>();
			while(rs.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderDetailId(rs.getInt("ORDER_DETAIL_ID"));
				orderDetail.setOrderId(rs.getInt("ORDER_ID"));
				orderDetail.setProdId(rs.getInt("PROD_ID"));
				orderDetail.setProdPrice(rs.getInt("PROD_PRICE"));
				orderDetail.setOrderProdQty(rs.getInt("ORDER_PROD_QTY"));
				orderDetail.setProdCommentScore(rs.getInt("PROD_COMMENT_SCORE"));
				orderDetail.setProdCommentText(rs.getString("PROD_COMMENT_TEXT"));
				orderDetail.setResProdReplyText(rs.getString("RES_PROD_REPLY_TEXT"));
				orderDetail.setProdCommentTimestamp(rs.getTimestamp("PROD_COMMENT_TIMESTAMP"));
				orderDetail.setResProdReplyTimestamp(rs.getTimestamp("RES_PROD_REPLY_TIMESTAMP"));
				orderDetail.setAccCouponId(rs.getInt("ACC_COUPON_ID"));
				list.add(orderDetail);
				}
			return list;
			} catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

	public List<OrderDetail> selectByProductId(String prodId) {
	    String sql = "SELECT * FROM ORDER_DETAIL WHERE PROD_ID = ?";
	    List<OrderDetail> orderDetails = new ArrayList<>();

	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, prodId);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                OrderDetail orderDetail = new OrderDetail();
	                orderDetail.setOrderDetailId(rs.getInt("ORDER_DETAIL_ID"));
	                orderDetail.setOrderId(rs.getInt("ORDER_ID"));
	                orderDetail.setProdId(rs.getInt("PROD_ID"));
	                orderDetail.setProdPrice(rs.getInt("PROD_PRICE"));
	                orderDetail.setOrderProdQty(rs.getInt("ORDER_PROD_QTY"));
	                orderDetail.setProdCommentScore(rs.getInt("PROD_COMMENT_SCORE"));
	                orderDetail.setProdCommentText(rs.getString("PROD_COMMENT_TEXT"));
	                orderDetail.setResProdReplyText(rs.getString("RES_PROD_REPLY_TEXT"));
	                orderDetail.setProdCommentTimestamp(rs.getTimestamp("PROD_COMMENT_TIMESTAMP"));
	                orderDetail.setResProdReplyTimestamp(rs.getTimestamp("RES_PROD_REPLY_TIMESTAMP"));
	                orderDetail.setAccCouponId(rs.getInt("ACC_COUPON_ID"));
	                orderDetails.add(orderDetail);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orderDetails;
	}
}
