package chooeat.prod.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;

import chooeat.prod.dao.OrderDao;
import chooeat.prod.model.vo.Order;
@Repository
@Import(DataSourceAutoConfiguration.class)
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private DataSource dataSource;
	@Override
	public int insert(Order order) {
	    String getMaxOrderIdQuery = "SELECT MAX(ORDER_ID) FROM `ORDER`";
	    String sql = "INSERT INTO `ORDER` (ORDER_ID, ACC_ID, ORDER_STATE, AMOUNT_BEFORE_COUPON, FINAL_AMOUNT, ORDER_TIMESTAMP, ORDER_INVOICE, TEX_ID_NUMBER, ORDER_NOTE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (Connection conn = dataSource.getConnection(); 
	         PreparedStatement getMaxOrderIdStmt = conn.prepareStatement(getMaxOrderIdQuery);
	         ResultSet maxOrderIdRs = getMaxOrderIdStmt.executeQuery();
	         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

	        int maxOrderId = 0;
	        if (maxOrderIdRs.next()) {
	            maxOrderId = maxOrderIdRs.getInt(1);
	        }
	        int newOrderId = maxOrderId + 1;

	        pstmt.setInt(1, newOrderId);
	        pstmt.setInt(2, order.getAccId());
	        pstmt.setInt(3, order.getOrderState() != 0 ? order.getOrderState() : 1); // Default OrderState to 1 if it's 0
	        pstmt.setInt(4, order.getAmountBeforeCoupon());
	        pstmt.setInt(5, order.getFinalAmount());
	        pstmt.setTimestamp(6, order.getOrderTimestamp() != null ? order.getOrderTimestamp() : new Timestamp(System.currentTimeMillis())); // Default OrderTimestamp to current time if it's null
	        pstmt.setString(7, order.getOrderInvoice() != null && !order.getOrderInvoice().isEmpty() ? order.getOrderInvoice() : generateRandomInvoice()); // Default OrderInvoice to generated random value if it's null or empty
	        pstmt.setString(8, order.getTexIdNumber());
	        pstmt.setString(9, order.getOrderNote());

	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating order failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int generatedOrderId = generatedKeys.getInt(1);
	                order.setOrderId(generatedOrderId);
	                return generatedOrderId;
	            } else {
	                throw new SQLException("Creating order failed, no ID obtained.");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1;
	}

	// Method to generate random OrderInvoice
	private String generateRandomInvoice() {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    Random random = new Random();
	    char firstChar = characters.charAt(random.nextInt(characters.length()));
	    char secondChar = characters.charAt(random.nextInt(characters.length()));
	    String numbers = String.format("%08d", random.nextInt(100000000));
	    return firstChar + "" + secondChar + "-" + numbers;
	}


	@Override
	public Order selectByaccId(Integer accId) {
		String sql = "select * from ORDER where ACC_ID = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(2, accId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("ORDER_ID"));
					order.setAccId(rs.getInt("ACC_ID"));
					order.setOrderState(rs.getInt("ORDER_STATE"));
					order.setAmountBeforeCoupon(rs.getInt("AMOUNT_BEFORE_COUPON"));
					order.setFinalAmount(rs.getInt("FINAL_AMOUNT"));
					order.setOrderTimestamp(rs.getTimestamp("ORDER_TIMESTAMP"));
					order.setOrderInvoice(rs.getString("ORDER_INVOICE"));
					order.setTexIdNumber(rs.getString("TEX_ID_NUMBER"));
					order.setOrderNote(rs.getString("ORDER_NOTE"));
					return order;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> selectAll() {
		String sql = "select * from ORDER";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			List<Order> list = new ArrayList<Order>();
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("ORDER_ID"));
				order.setAccId(rs.getInt("ACC_ID"));
				order.setOrderState(rs.getInt("ORDER_STATE"));
				order.setAmountBeforeCoupon(rs.getInt("AMOUNT_BEFORE_COUPON"));
				order.setFinalAmount(rs.getInt("FINAL_AMOUNT"));
				order.setOrderTimestamp(rs.getTimestamp("ORDER_TIMESTAMP"));
				order.setOrderInvoice(rs.getString("ORDER_INVOICE"));
				order.setTexIdNumber(rs.getString("TEX_ID_NUMBER"));
				order.setOrderNote(rs.getString("ORDER_NOTE"));
				list.add(order);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
