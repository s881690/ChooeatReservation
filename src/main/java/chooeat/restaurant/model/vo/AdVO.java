package chooeat.restaurant.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class AdVO {
    private Integer adId;
    private Integer restaurantId;
    private Timestamp adApplyTimestamp;
    private Date adStartDate;
    private Date adEndDate;
    private Integer adAmount;
    private Integer adPlan;
    private Integer adCheck;
    private Timestamp adCheckTimestamp;
    private Integer adminId;  
    
	public AdVO() {		
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Timestamp getAdApplyTimestamp() {
		return adApplyTimestamp;
	}
	public void setAdApplyTimestamp(Timestamp adApplyTimestamp) {
		this.adApplyTimestamp = adApplyTimestamp;
	}
	public Date getAdStartDate() {
		return adStartDate;
	}
	public void setAdStartDate(Date adStartDate) {
		this.adStartDate = adStartDate;
	}
	public Date getAdEndDate() {
		return adEndDate;
	}
	public void setAdEndDate(Date adEndDate) {
		this.adEndDate = adEndDate;
	}
	public Integer getAdAmount() {
		return adAmount;
	}
	public void setAdAmount(Integer adAmount) {
		this.adAmount = adAmount;
	}
	public Integer getAdPlan() {
		return adPlan;
	}
	public void setAdPlan(Integer adPlan) {
		this.adPlan = adPlan;
	}
	public Integer getAdCheck() {
		return adCheck;
	}
	public void setAdCheck(Integer adCheck) {
		this.adCheck = adCheck;
	}
	public Timestamp getAdCheckTimestamp() {
		return adCheckTimestamp;
	}
	public void setAdCheckTimestamp(Timestamp adCheckTimestamp) {
		this.adCheckTimestamp = adCheckTimestamp;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
    
}