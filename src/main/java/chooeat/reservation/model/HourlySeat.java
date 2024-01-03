package chooeat.reservation.model;


public class HourlySeat {
	private Integer hour;
	private Integer remainSeat;
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public Integer getRemainSeat() {
		return remainSeat;
	}
	public void setRemainSeat(Integer remainSeat) {
		this.remainSeat = remainSeat;
	}
	public HourlySeat(Integer hour, Integer remainSeat) {
		super();
		this.hour = hour;
		this.remainSeat = remainSeat;
	}
	public HourlySeat() {}
}
