package chooeat.restaurant.model.vo;

public class ResTypeDetailVO {
	private Integer resTypeDetailId;
	private Integer restaurantId;
	private Integer resTypeId;

	public ResTypeDetailVO() {
	}

	public Integer getResTypeDetailId() {
		return resTypeDetailId;
	}

	public void setResTypeDetailId(Integer resTypeDetailId) {
		this.resTypeDetailId = resTypeDetailId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Integer getResTypeId() {
		return resTypeId;
	}

	public void setResTypeId(Integer resTypeId) {
		this.resTypeId = resTypeId;
	}
	
}