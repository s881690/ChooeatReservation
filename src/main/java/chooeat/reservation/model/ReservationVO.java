package chooeat.reservation.model;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reservation")
@Component
public class ReservationVO {

	@Id
	@Column(name = "reservation_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservationId;

	@Column(name = "acc_id")
	private Integer accId;

	@Column(name = "restaurant_id")
	private Integer restaurantId;

	@SerializedName("ppl")
	@Column(name = "reservation_number")
	private Integer reservationNumber;

	@Column(name = "reservation_date_starttime")
	@SerializedName("date_time")
	private Timestamp reservationDateStartTime;

	@Column(name = "reservation_date_endtime")
	private Timestamp reservationDateEndTime;

	@Column(name = "reservation_note")
	@SerializedName("text")
	private String reservationNote;

	@Column(name = "reservation_state")
	private Integer reservationState;

	@Column(name = "restaurant_comment_datetime")
	private Timestamp restaurantCommentDatetime;

	@Column(name = "restaurant_comment_text")
	private String restaurantCommentText;

	@Column(name = "restaurant_comment_score")
	private Integer restaurantCommentScore;

	@Column(name = "restaurant_comment_reply_datetime")
	private Timestamp restaurantCommentReplyDatetime;

	@Column(name = "restaurant_comment_reply_text")
	private String restaurantCommentReplyText;
	@Column(name = "isComment")
	private Boolean isComment;
	@Column(name = "isNotify")
	private Integer isNotify;

	public Integer getIsNotify() {
		return isNotify;
	}

	public void setIsNotify(Integer isNotify) {
		this.isNotify = isNotify;
	}

	// Constructor
	public ReservationVO() {
	}

	

	

	public ReservationVO(Integer reservationId, Integer accId, Integer restaurantId, Integer reservationNumber,
			Timestamp reservationDateStartTime, Timestamp reservationDateEndTime, String reservationNote,
			Integer reservationState, Timestamp restaurantCommentDatetime, String restaurantCommentText,
			Integer restaurantCommentScore, Timestamp restaurantCommentReplyDatetime, String restaurantCommentReplyText,
			Boolean isComment, Integer isNotify) {
		super();
		this.reservationId = reservationId;
		this.accId = accId;
		this.restaurantId = restaurantId;
		this.reservationNumber = reservationNumber;
		this.reservationDateStartTime = reservationDateStartTime;
		this.reservationDateEndTime = reservationDateEndTime;
		this.reservationNote = reservationNote;
		this.reservationState = reservationState;
		this.restaurantCommentDatetime = restaurantCommentDatetime;
		this.restaurantCommentText = restaurantCommentText;
		this.restaurantCommentScore = restaurantCommentScore;
		this.restaurantCommentReplyDatetime = restaurantCommentReplyDatetime;
		this.restaurantCommentReplyText = restaurantCommentReplyText;
		this.isComment = isComment;
		this.isNotify = isNotify;
	}

	@Override
	public String toString() {
		return "ReservationVO [accId=" + accId + ", restaurantId=" + restaurantId + ", reservationNumber="
				+ reservationNumber + ", reservationDateStartTime=" + reservationDateStartTime + ", reservationNote="
				+ reservationNote + "]";
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Integer getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(Integer reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public Timestamp getReservationDateStartTime() {
		return reservationDateStartTime;
	}

	public void setReservationDateStartTime(Timestamp reservationDateStartTime) {
		this.reservationDateStartTime = reservationDateStartTime;
	}

	public Timestamp getReservationDateEndTime() {
		return reservationDateEndTime;
	}

	public void setReservationDateEndTime(Timestamp reservationDateEndTime) {
		this.reservationDateEndTime = reservationDateEndTime;
	}

	public String getReservationNote() {
		return reservationNote;
	}

	public void setReservationNote(String reservationNote) {
		this.reservationNote = reservationNote;
	}

	public Integer getReservationState() {
		return reservationState;
	}

	public void setReservationState(Integer reservationState) {
		this.reservationState = reservationState;
	}

	public Timestamp getRestaurantCommentDatetime() {
		return restaurantCommentDatetime;
	}

	public void setRestaurantCommentDatetime(Timestamp restaurantCommentDatetime) {
		this.restaurantCommentDatetime = restaurantCommentDatetime;
	}

	public String getRestaurantCommentText() {
		return restaurantCommentText;
	}

	public void setRestaurantCommentText(String restaurantCommentText) {
		this.restaurantCommentText = restaurantCommentText;
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

	public String getRestaurantCommentReplyText() {
		return restaurantCommentReplyText;
	}

	public void setRestaurantCommentReplyText(String restaurantCommentReplyText) {
		this.restaurantCommentReplyText = restaurantCommentReplyText;
	}

	public Boolean getIsComment() {
		return isComment;
	}

	public void setIsComment(Boolean isComment) {
		this.isComment = isComment;
	}

}
