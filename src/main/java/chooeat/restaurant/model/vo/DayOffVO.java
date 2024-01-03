package chooeat.restaurant.model.vo;

import java.sql.Date;

public class DayOffVO {
    private int dayoff_id;
    private int restaurant_id;
    private Date dayoff;
    
	public DayOffVO() {
		super();
		}

	public int getDayoff_id() {
		return dayoff_id;
	}

	public void setDayoff_id(int dayoff_id) {
		this.dayoff_id = dayoff_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Date getDayoff() {
		return dayoff;
	}

	public void setDayoff(Date dayoff) {
		this.dayoff = dayoff;
	}
  
}