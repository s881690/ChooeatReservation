package chooeat.admin.web.order.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import chooeat.admin.core.pojo.Core;
import chooeat.admin.web.prod.pojo.ProdVO;

@Entity
@Table(name = "order_detail")
public class AdminOrderDetailVO extends Core{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Integer orderDetailId;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "prod_id")
	private Integer prodId;
	
	@Column(name = "prod_price")
	private Integer prodPrice;
	
	@Column(name = "order_prod_qty")
	private Integer orderProdQty;
	
	@OneToOne
	@JoinColumn(name = "prod_id", insertable = false, updatable = false)
	private ProdVO prodVO;

	public AdminOrderDetailVO(Integer orderDetailId, Integer orderId, Integer prodId, Integer prodPrice,
			Integer orderProdQty, ProdVO prodVO) {
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.prodId = prodId;
		this.prodPrice = prodPrice;
		this.orderProdQty = orderProdQty;
		this.prodVO = prodVO;
	}
	
	public AdminOrderDetailVO() {
		
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Integer getOrderProdQty() {
		return orderProdQty;
	}

	public void setOrderProdQty(Integer orderProdQty) {
		this.orderProdQty = orderProdQty;
	}

	public ProdVO getProdVO() {
		return prodVO;
	}

	public void setProdVO(ProdVO prodVO) {
		this.prodVO = prodVO;
	}
	
}
