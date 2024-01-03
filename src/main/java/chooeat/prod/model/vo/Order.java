package chooeat.prod.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer accId;
	private Integer orderState;
	private Integer amountBeforeCoupon;
	private Integer finalAmount;
	private Timestamp orderTimestamp;
	private String orderInvoice;
	private String texIdNumber;
	private String orderNote;
	
	public Order() {
	}

	public Order(Integer orderId, Integer accId, Integer orderState, Integer amountBeforeCoupon, Integer finalAmount,
			Timestamp orderTimestamp, String orderInvoice, String texIdNumber, String orderNote) {
		super();
		this.orderId = orderId;
		this.accId = accId;
		this.orderState = orderState;
		this.amountBeforeCoupon = amountBeforeCoupon;
		this.finalAmount = finalAmount;
		this.orderTimestamp = orderTimestamp;
		this.orderInvoice = orderInvoice;
		this.texIdNumber = texIdNumber;
		this.orderNote = orderNote;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Integer getAmountBeforeCoupon() {
		return amountBeforeCoupon;
	}

	public void setAmountBeforeCoupon(Integer amountBeforeCoupon) {
		this.amountBeforeCoupon = amountBeforeCoupon;
	}

	public Integer getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Integer finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Timestamp getOrderTimestamp() {
		return orderTimestamp;
	}

	public void setOrderTimestamp(Timestamp orderTimestamp) {
		this.orderTimestamp = orderTimestamp;
	}

	public String getOrderInvoice() {
		return orderInvoice;
	}

	public void setOrderInvoice(String orderInvoice) {
		this.orderInvoice = orderInvoice;
	}

	public String getTexIdNumber() {
		return texIdNumber;
	}

	public void setTexIdNumber(String texIdNumber) {
		this.texIdNumber = texIdNumber;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
