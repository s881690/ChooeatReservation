package chooeat.reservation.model;

import org.springframework.stereotype.Component;

@Component
public class EmailInfo {
	private Integer reservationId;
	private String recipientName;
	private Integer reservationNumber;
	private String reservationTime;
	private String restaurantName;
	private String restaurantPhone;
	private Integer isNotify;
	
	
	public Integer getIsNotify() {
		return isNotify;
	}

	public void setIsNotify(Integer isNotify) {
		this.isNotify = isNotify;
	}

	public String getRestaurantPhone() {
		return restaurantPhone;
	}

	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}

	private String restaurantAddress;
	private String recipient;

	public EmailInfo() {
	}

	public EmailInfo(Integer reservationId, String recipientName, Integer reservationNumber, String reservationTime,
			String restaurantName, String restaurantAddress, String recipient) {
		super();
		this.reservationId = reservationId;
		this.recipientName = recipientName;
		this.reservationNumber = reservationNumber;
		this.reservationTime = reservationTime;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.recipient = recipient;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public Integer getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(Integer reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public String getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

}
