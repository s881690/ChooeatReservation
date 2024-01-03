package chooeat.admin.web.order.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import chooeat.admin.web.acc.pojo.AdminAccountVO;


@Entity
@Table(name = "`order`")
public class AdminOrderVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", updatable = false)
	private Integer orderId;
	
	@Column(name = "acc_id")
	private Integer accId;
	
	@Column(name = "order_state")
	private Integer orderState;
	
	@Column(name = "amount_before_coupon")
	private Integer amountBeforeCoupon;
	
	@Column(name = "final_amount")
	private Integer finalAmount;
	
	@Column(name = "order_timestamp")
	private Timestamp orderTimestamp;
	
	@Column(name = "order_invoice")
	private String orderInvoice;
	
	@Column(name = "tex_id_number")
	private String texIdNumber;
	
	@Column(name = "order_note")
	private String orderNote;
	
	@OneToOne
	@JoinColumn(name = "acc_id", insertable = false, updatable = false)
	private AdminAccountVO adminAccountVO;
	
	public AdminOrderVO(Integer orderId, Integer accId, Integer orderState, Integer amountBeforeCoupon, Integer finalAmount,
			Timestamp orderTimestamp, String orderInvoice, String texIdNumber, String orderNote, AdminAccountVO adminAccountVO) {
		this.orderId = orderId;
		this.accId = accId;
		this.orderState = orderState;
		this.amountBeforeCoupon = amountBeforeCoupon;
		this.finalAmount = finalAmount;
		this.orderTimestamp = orderTimestamp;
		this.orderInvoice = orderInvoice;
		this.texIdNumber = texIdNumber;
		this.orderNote = orderNote;
		this.adminAccountVO = adminAccountVO;
	}
	public AdminOrderVO() {
		
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
	public AdminAccountVO getAdminAccountVO() {
		return adminAccountVO;
	}
	public void setAdminAccountVO(AdminAccountVO adminAccountVO) {
		this.adminAccountVO = adminAccountVO;
	}
}
