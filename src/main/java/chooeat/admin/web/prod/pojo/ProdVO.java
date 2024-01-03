package chooeat.admin.web.prod.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import chooeat.admin.core.pojo.Core;
import chooeat.admin.web.restaurant.pojo.AdminRestaurantVO;

@Entity
@Table(name = "prod")
public class ProdVO extends Core{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_id", updatable = false)
	private Integer prodId;
	
	@Column(name = "restaurant_id")
    private Integer restaurantId;
	
	@Column(name = "prod_name")
    private String prodName;
	
	@Column(name = "prod_text")
    private String prodText;
	
	@Column(name = "prod_userguide")
    private String prodUserGuide;
	
	@Column(name = "prod_price")
    private Integer prodPrice;
	
	@Column(name = "prod_qty")
    private Integer prodQty;
	
	@Column(name = "prod_state")
    private Integer prodState;
	
	@Lob
	@Column(name = "prod_pic")
    private Byte[] prodPic;
	
	@Column(name = "prod_comment_number")
    private Integer prodCommentNumber;
	
	@Column(name = "prod_comment_score")
    private Integer prodCommentScore;
	
	@OneToOne
	@JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
	private AdminRestaurantVO adminRestaurantVO;
    
	public ProdVO(Integer prodId, Integer restaurantId, String prodName, String prodText, String prodUserGuide,
			Integer prodPrice, Integer prodQty, Integer prodState, Byte[] prodPic, Integer prodCommentNumber,
			Integer prodCommentScore, AdminRestaurantVO adminRestaurantVO) {
		this.prodId = prodId;
		this.restaurantId = restaurantId;
		this.prodName = prodName;
		this.prodText = prodText;
		this.prodUserGuide = prodUserGuide;
		this.prodPrice = prodPrice;
		this.prodQty = prodQty;
		this.prodState = prodState;
		this.prodPic = prodPic;
		this.prodCommentNumber = prodCommentNumber;
		this.prodCommentScore = prodCommentScore;
		this.adminRestaurantVO = adminRestaurantVO;
	}
	public ProdVO() {
		
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
	public String getProdUserGuide() {
		return prodUserGuide;
	}
	public void setProdUserGuide(String prodUserGuide) {
		this.prodUserGuide = prodUserGuide;
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
	public Byte[] getProdPic() {
		return prodPic;
	}
	public void setProdPic(Byte[] prodPic) {
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
	public AdminRestaurantVO getRestaurantVO() {
		return adminRestaurantVO;
	}
	public void setRestaurantVO(AdminRestaurantVO adminRestaurantVO) {
		this.adminRestaurantVO = adminRestaurantVO;
	}

}
