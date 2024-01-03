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

import chooeat.prod.dao.ProdOrderDetailDao;
import chooeat.prod.model.vo.ProdOrderDetail;
@Repository
@Import(DataSourceAutoConfiguration.class)
public class ProdOrderDetailDaoImpl implements ProdOrderDetailDao {
	@Autowired
	private DataSource dataSource;

	@Override
	public int insert(ProdOrderDetail prodOrderDetail) {
		String getMaxProdOrderDetailIdQuery = "SELECT MAX(PROD_ORDER_DETAIL_ID) FROM `PROD_ORDER_DETAIL`";
		String sql = "insert into PROD_ORDER_DETAIL values(?, ?, ?, ?)";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement getMaxProdOrderDetailIdStmt = conn.prepareStatement(getMaxProdOrderDetailIdQuery);
				ResultSet maxProdOrderDetailIdRs = getMaxProdOrderDetailIdStmt.executeQuery();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			
			int maxProdOrderDetailId = 0;
			if (maxProdOrderDetailIdRs.next()) {
				maxProdOrderDetailId = maxProdOrderDetailIdRs.getInt(1);
			}
			int newProdOrderDetailId = maxProdOrderDetailId + 1;

			pstmt.setInt(1, newProdOrderDetailId);
			pstmt.setInt(2, prodOrderDetail.getOrderDetailId());
			pstmt.setString(3, prodOrderDetail.getProdOrderCode());
			pstmt.setInt(4, prodOrderDetail.getProdOrderState());
	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating order failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int generatedProdOrderDetailId = generatedKeys.getInt(1);
	                prodOrderDetail.setProdOrderDetailId(generatedProdOrderDetailId);
	                return generatedProdOrderDetailId;
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
	public List<ProdOrderDetail> selectAll() {
		String sql = "select * from PROD_ORDER_DETAIL";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			List<ProdOrderDetail> list = new ArrayList<ProdOrderDetail>();
			while (rs.next()) {
				ProdOrderDetail prodOrderDetail = new ProdOrderDetail();
				prodOrderDetail.setProdOrderDetailId(rs.getInt("PROD_ORDER_DETAIL_ID"));
				prodOrderDetail.setOrderDetailId(rs.getInt("ORDER_DETAIL_ID"));
				prodOrderDetail.setProdOrderCode(rs.getString("PROD_ORDER_CODE"));
				prodOrderDetail.setProdOrderState(rs.getInt("PROD_ORDER_STATE"));
				list.add(prodOrderDetail);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
