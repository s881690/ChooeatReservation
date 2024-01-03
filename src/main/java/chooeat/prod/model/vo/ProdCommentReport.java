package chooeat.prod.model.vo;

import java.io.Serializable;

public class ProdCommentReport implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer prodCommentReportId;
	private Integer accId;
	private Integer orderDetailId;
	private String prodCommentReportReason;

	public ProdCommentReport() {
	}

	public ProdCommentReport(Integer prodCommentReportId, Integer accId, Integer orderDetailId,
			String prodCommentReportReason) {
		super();
		this.prodCommentReportId = prodCommentReportId;
		this.accId = accId;
		this.orderDetailId = orderDetailId;
		this.prodCommentReportReason = prodCommentReportReason;
	}

	public Integer getProdCommentReportId() {
		return prodCommentReportId;
	}

	public void setProdCommentReportId(Integer prodCommentReportId) {
		this.prodCommentReportId = prodCommentReportId;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getProdCommentReportReason() {
		return prodCommentReportReason;
	}

	public void setProdCommentReportReason(String prodCommentReportReason) {
		this.prodCommentReportReason = prodCommentReportReason;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
