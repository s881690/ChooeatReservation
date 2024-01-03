package chooeat.reservation.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Result {

	private String status;
	private String resStartTime;
	private String resEndTime;
	private List<HourlySeat> HourlySeatlist;
	private List<Integer> reservedList;
	private Integer reservationId;
	private Integer reservedPeople;
	private String member;
	private String restaurantName;
	private Integer remainSeat;
	private String index;
	
	
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public Integer getRemainSeat() {
		return remainSeat;
	}
	public void setRemainSeat(Integer remainSeat) {
		this.remainSeat = remainSeat;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public Integer getReservedPeople() {
		return reservedPeople;
	}
	public void setReservedPeople(Integer reservedPeople) {
		this.reservedPeople = reservedPeople;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResStartTime() {
		return resStartTime;
	}
	public void setResStartTime(String resStartTime) {
		this.resStartTime = resStartTime;
	}
	public String getResEndTime() {
		return resEndTime;
	}
	public void setResEndTime(String resEndTime) {
		this.resEndTime = resEndTime;
	}
	public List<HourlySeat> getHourlySeatlist() {
		return HourlySeatlist;
	}
	public void setHourlySeatlist(List<HourlySeat> hourlySeatlist) {
		HourlySeatlist = hourlySeatlist;
	}
	public List<Integer> getReservedList() {
		return reservedList;
	}
	public void setReservedList(List<Integer> reservedList) {
		this.reservedList = reservedList;
	}

	
}
