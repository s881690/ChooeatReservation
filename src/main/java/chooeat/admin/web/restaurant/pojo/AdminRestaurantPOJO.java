package chooeat.admin.web.restaurant.pojo;

import chooeat.admin.core.pojo.Core;

public class AdminRestaurantPOJO extends Core{
	private static final long serialVersionUID = 1L;

	private Integer restaurantId;
	private String resName;
	private String resType;
	private Integer resState;
	
	public AdminRestaurantPOJO(Integer restaurantId, String resName, String resType, Integer resState) {
		this.restaurantId = restaurantId;
		this.resName = resName;
		this.resType = resType;
		this.resState = resState;
	}
	
	public AdminRestaurantPOJO() {
		
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public Integer getResState() {
		return resState;
	}

	public void setResState(Integer resState) {
		this.resState = resState;
	}
	
	
}
