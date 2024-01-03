package chooeat.admin.web.reservation.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import chooeat.admin.core.pojo.Core;

@Entity
@Table(name = "reservation")
public class AdminResCommentPOJO extends Core{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id", updatable = false)
	private Integer reservationId;
	
	@Column(name = "restaurant_comment_datetime")
	private Timestamp restaurantCommentDatetime;
	
	@Column(name = "restaurant_id")
	private Integer restaurantId;
	
	@Column(name = "acc_id")
	private Integer accId;
	
	@Column(name = "acc_name")
	private String accName;
	
	@Column(name = "isComment")
	private Boolean isComment;
	
	@Column(name = "restaurant_comment_score")
	private Integer restaurantCommentScore;
	
	@Column(name = "restaurant_comment_reply_datetime")
	private Timestamp restaurantCommentReplyDatetime;
	
	public AdminResCommentPOJO(Integer reservationId, Timestamp restaurantCommentDatetime, Integer restaurantId,
			Integer accId, String accName, Boolean isComment, Integer restaurantCommentScore,
			Timestamp restaurantCommentReplyDatetime) {
		super();
		this.reservationId = reservationId;
		this.restaurantCommentDatetime = restaurantCommentDatetime;
		this.restaurantId = restaurantId;
		this.accId = accId;
		this.accName = accName;
		this.isComment = isComment;
		this.restaurantCommentScore = restaurantCommentScore;
		this.restaurantCommentReplyDatetime = restaurantCommentReplyDatetime;
	}
	
	public AdminResCommentPOJO() {
		
	}
	
	public Integer getreservationId() {
		return reservationId;
	}
	public void setreservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public Timestamp getRestaurantCommentDatetime() {
		return restaurantCommentDatetime;
	}
	public void setRestaurantCommentDatetime(Timestamp restaurantCommentDatetime) {
		this.restaurantCommentDatetime = restaurantCommentDatetime;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Boolean getIsComment() {
		return isComment;
	}
	public void setIsComment(Boolean isComment) {
		this.isComment = isComment;
	}
	public Integer getRestaurantCommentScore() {
		return restaurantCommentScore;
	}
	public void setRestaurantCommentScore(Integer restaurantCommentScore) {
		this.restaurantCommentScore = restaurantCommentScore;
	}
	public Timestamp getRestaurantCommentReplyDatetime() {
		return restaurantCommentReplyDatetime;
	}
	public void setRestaurantCommentReplyDatetime(Timestamp restaurantCommentReplyDatetime) {
		this.restaurantCommentReplyDatetime = restaurantCommentReplyDatetime;
	}
	
}
