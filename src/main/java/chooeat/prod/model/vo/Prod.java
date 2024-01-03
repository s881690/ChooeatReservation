package chooeat.prod.model.vo;

import java.io.Serializable;
import java.util.Arrays;

public class Prod implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer prodId;
	private Integer restaurantId;
	private String prodName;
	private String prodText;
	private String prodUserguide;
	private Integer prodPrice;
	private Integer prodQty;
	private Integer prodState;
	private byte[] prodPic;
	private Integer prodCommentNumber;
	private Integer prodCommentScore;
	private String resName;
	private String resAdd;
	private Integer resTypeId;
	private String resType;
	
	public Prod() {
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdText() {
		return prodText;
	}

	public void setProdText(String prodText) {
		this.prodText = prodText;
	}

	public String getProdUserguide() {
		return prodUserguide;
	}

	public void setProdUserguide(String prodUserguide) {
		this.prodUserguide = prodUserguide;
	}

	public Integer getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Integer getProdQty() {
		return prodQty;
	}

	public void setProdQty(Integer prodQty) {
		this.prodQty = prodQty;
	}

	public Integer getProdState() {
		return prodState;
	}

	public void setProdState(Integer prodState) {
		this.prodState = prodState;
	}

	public byte[] getProdPic() {
		return prodPic;
	}

	public void setProdPic(byte[] prodPic) {
		this.prodPic = prodPic;
	}

	public Integer getProdCommentNumber() {
		return prodCommentNumber;
	}

	public void setProdCommentNumber(Integer prodCommentNumber) {
		this.prodCommentNumber = prodCommentNumber;
	}

	public Integer getProdCommentScore() {
		return prodCommentScore;
	}

	public void setProdCommentScore(Integer prodCommentScore) {
		this.prodCommentScore = prodCommentScore;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResAdd() {
		return resAdd;
	}

	public void setResAdd(String resAdd) {
		this.resAdd = resAdd;
	}

	public Integer getResTypeId() {
		return resTypeId;
	}

	public void setResTypeId(Integer resTypeId) {
		this.resTypeId = resTypeId;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Prod [prodId=" + prodId + ", restaurantId=" + restaurantId + ", prodName=" + prodName + ", prodText="
				+ prodText + ", prodUserguide=" + prodUserguide + ", prodPrice=" + prodPrice + ", prodQty=" + prodQty
				+ ", prodState=" + prodState + ", prodPic=" + Arrays.toString(prodPic) + ", prodCommentNumber="
				+ prodCommentNumber + ", prodCommentScore=" + prodCommentScore + ", resName=" + resName + ", resAdd="
				+ resAdd + ", resTypeId=" + resTypeId + ", resType=" + resType + "]";
	}

	public Prod(Integer prodId, Integer restaurantId, String prodName, String prodText, String prodUserguide,
			Integer prodPrice, Integer prodQty, Integer prodState, byte[] prodPic, Integer prodCommentNumber,
			Integer prodCommentScore, String resName, String resAdd, Integer resTypeId, String resType) {
		super();
		this.prodId = prodId;
		this.restaurantId = restaurantId;
		this.prodName = prodName;
		this.prodText = prodText;
		this.prodUserguide = prodUserguide;
		this.prodPrice = prodPrice;
		this.prodQty = prodQty;
		this.prodState = prodState;
		this.prodPic = prodPic;
		this.prodCommentNumber = prodCommentNumber;
		this.prodCommentScore = prodCommentScore;
		this.resName = resName;
		this.resAdd = resAdd;
		this.resTypeId = resTypeId;
		this.resType = resType;
	}

	
}


