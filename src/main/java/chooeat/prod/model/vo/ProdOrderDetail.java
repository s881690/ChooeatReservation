package chooeat.prod.model.vo;

import java.io.Serializable;

public class ProdOrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer prodOrderDetailId;
	private Integer orderDetailId;
	private String prodOrderCode;
	private Integer prodOrderState;

	public ProdOrderDetail() {
	}

	public ProdOrderDetail(Integer prodOrderDetailId, Integer orderDetailId, String prodOrderCode,
			Integer prodOrderState) {
		super();
		this.prodOrderDetailId = prodOrderDetailId;
		this.orderDetailId = orderDetailId;
		this.prodOrderCode = prodOrderCode;
		this.prodOrderState = prodOrderState;
	}

	public Integer getProdOrderDetailId() {
		return prodOrderDetailId;
	}

	public void setProdOrderDetailId(Integer prodOrderDetailId) {
		this.prodOrderDetailId = prodOrderDetailId;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getProdOrderCode() {
		return prodOrderCode;
	}

	public void setProdOrderCode(String prodOrderCode) {
		this.prodOrderCode = prodOrderCode;
	}

	public Integer getProdOrderState() {
		return prodOrderState;
	}

	public void setProdOrderState(Integer prodOrderState) {
		this.prodOrderState = prodOrderState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
