package chooeat.admin.web.reservation.pojo;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

import chooeat.admin.core.pojo.Core;
import chooeat.admin.web.acc.pojo.AdminAccountVO;

@Entity
@Table(name = "reservation")
public class AdminReservationVO extends Core {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id", updatable = false)
	private Integer reservationId;

	@Column(name = "acc_id")
	private Integer accId;

	@Column(name = "restaurant_id")
	private Integer restaurantId;

	@Column(name = "reservation_number")
	private Integer reservationNumber;

	@Column(name = "reservation_date_starttime")
	private Time reservationStartTime;

	@Column(name = "reservation_date_endtime")
	private Time reservationEndTime;

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

	@OneToOne
	@JoinColumn(name = "acc_id", insertable = false, updatable = false)
	private AdminAccountVO accountVO;
	
	@OneToOne
	@JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
	private AdminReservationVO restaurantVO;

	public AdminReservationVO(Integer reservationId, Integer accId, Integer restaurantId, Integer reservationNumber,
			Time reservationStartTime, Time reservationEndTime, String reservationNote,
			Integer reservationState, Timestamp restaurantCommentDatetime, String restaurantCommentText,
			Integer restaurantCommentScore, Timestamp restaurantCommentReplyDatetime, String restaurantCommentReplyText,
			AdminAccountVO accountVO, AdminReservationVO restaurantVO) {
		this.reservationId = reservationId;
		this.accId = accId;
		this.restaurantId = restaurantId;
		this.reservationNumber = reservationNumber;
		this.reservationStartTime = reservationStartTime;
		this.reservationEndTime = reservationEndTime;
		this.reservationNote = reservationNote;
		this.reservationState = reservationState;
		this.restaurantCommentDatetime = restaurantCommentDatetime;
		this.restaurantCommentText = restaurantCommentText;
		this.restaurantCommentScore = restaurantCommentScore;
		this.restaurantCommentReplyDatetime = restaurantCommentReplyDatetime;
		this.restaurantCommentReplyText = restaurantCommentReplyText;
		this.accountVO = accountVO;
		this.restaurantVO = restaurantVO;
	}
	
	public AdminReservationVO() {
		
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

	public Time getReservationStartTime() {
		return reservationStartTime;
	}

	public void setReservationStartTime(Time reservationStartTime) {
		this.reservationStartTime = reservationStartTime;
	}

	public Time getReservationEndTime() {
		return reservationEndTime;
	}

	public void setReservationEndTime(Time reservationEndTime) {
		this.reservationEndTime = reservationEndTime;
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

	public AdminAccountVO getAccountVO() {
		return accountVO;
	}

	public void setAccountVO(AdminAccountVO accountVO) {
		this.accountVO = accountVO;
	}

	public AdminReservationVO getRestaurantVO() {
		return restaurantVO;
	}

	public void setRestaurantVO(AdminReservationVO restaurantVO) {
		this.restaurantVO = restaurantVO;
	}
	
}
