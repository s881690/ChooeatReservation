package chooeat.prod.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer orderDetailId;
	private Integer orderId;
	private Integer prodId;
	private Integer prodPrice;
	private Integer orderProdQty;
	private Integer prodCommentScore;
	private String prodCommentText;
	private String resProdReplyText;
	private Timestamp prodCommentTimestamp;
	private Timestamp resProdReplyTimestamp;
	private Integer accCouponId;
	private String accName;
	
	public OrderDetail() {
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", prodId=" + prodId
				+ ", prodPrice=" + prodPrice + ", orderProdQty=" + orderProdQty + ", prodCommentScore="
				+ prodCommentScore + ", prodCommentText=" + prodCommentText + ", resProdReplyText=" + resProdReplyText
				+ ", prodCommentTimestamp=" + prodCommentTimestamp + ", resProdReplyTimestamp=" + resProdReplyTimestamp
				+ ", accCouponId=" + accCouponId + ", accName=" + accName + "]";
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

	public Integer getProdCommentScore() {
		return prodCommentScore;
	}

	public void setProdCommentScore(Integer prodCommentScore) {
		this.prodCommentScore = prodCommentScore;
	}

	public String getProdCommentText() {
		return prodCommentText;
	}

	public void setProdCommentText(String prodCommentText) {
		this.prodCommentText = prodCommentText;
	}

	public String getResProdReplyText() {
		return resProdReplyText;
	}

	public void setResProdReplyText(String resProdReplyText) {
		this.resProdReplyText = resProdReplyText;
	}

	public Timestamp getProdCommentTimestamp() {
		return prodCommentTimestamp;
	}

	public void setProdCommentTimestamp(Timestamp prodCommentTimestamp) {
		this.prodCommentTimestamp = prodCommentTimestamp;
	}

	public Timestamp getResProdReplyTimestamp() {
		return resProdReplyTimestamp;
	}

	public void setResProdReplyTimestamp(Timestamp resProdReplyTimestamp) {
		this.resProdReplyTimestamp = resProdReplyTimestamp;
	}

	public Integer getAccCouponId() {
		return accCouponId;
	}

	public void setAccCouponId(Integer accCouponId) {
		this.accCouponId = accCouponId;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public OrderDetail(Integer orderDetailId, Integer orderId, Integer prodId, Integer prodPrice, Integer orderProdQty,
			Integer prodCommentScore, String prodCommentText, String resProdReplyText, Timestamp prodCommentTimestamp,
			Timestamp resProdReplyTimestamp, Integer accCouponId, String accName) {
		super();
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.prodId = prodId;
		this.prodPrice = prodPrice;
		this.orderProdQty = orderProdQty;
		this.prodCommentScore = prodCommentScore;
		this.prodCommentText = prodCommentText;
		this.resProdReplyText = resProdReplyText;
		this.prodCommentTimestamp = prodCommentTimestamp;
		this.resProdReplyTimestamp = resProdReplyTimestamp;
		this.accCouponId = accCouponId;
		this.accName = accName;
	}
	
}