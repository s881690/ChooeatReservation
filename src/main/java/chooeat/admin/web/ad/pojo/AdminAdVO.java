package chooeat.admin.web.ad.pojo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import chooeat.admin.web.acc.pojo.AdminAccountVO;
import chooeat.admin.web.admin.pojo.AdminVO;
import chooeat.admin.web.restaurant.pojo.AdminRestaurantVO;

@Entity
@Table(name = "Ad")
public class AdminAdVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ad_id", updatable = false)
	private Integer adId;
	
	@Column(name = "restaurant_id")
	private Integer restaurantId;
	
	@Column(name = "ad_apply_timestamp")
	private Timestamp adApplyTimestamp;
	
	@Column(name = "ad_start_date")
	private Date adStartDate;
	
	@Column(name = "ad_end_date")
	private Date adEndDate;
	
	@Column(name = "ad_amount")
	private Integer adAmount;
	
	@Column(name = "ad_plan")
	private Integer adPlan;
	
	@Column(name = "ad_check")
	private Integer adCheck;
	
	@Column(name = "ad_check_timestamp")
	private Timestamp adCheckTimestamp;
	
	@Column(name = "admin_id")
	private Integer adminId;
	
	@OneToOne
	@JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
	private AdminRestaurantVO adminRestaurantVO;
	
	@OneToOne
	@JoinColumn(name = "admin_id", insertable = false, updatable = false)
	private AdminVO adminVO;

	public AdminAdVO(Integer adId, Integer restaurantId, Timestamp adApplyTimestamp, Date adStartDate, Date adEndDate,
			Integer adAmount, Integer adPlan, Integer adCheck, Timestamp adCheckTimestamp, Integer adminId,
			AdminRestaurantVO adminRestaurantVO, AdminVO adminVO) {
		this.adId = adId;
		this.restaurantId = restaurantId;
		this.adApplyTimestamp = adApplyTimestamp;
		this.adStartDate = adStartDate;
		this.adEndDate = adEndDate;
		this.adAmount = adAmount;
		this.adPlan = adPlan;
		this.adCheck = adCheck;
		this.adCheckTimestamp = adCheckTimestamp;
		this.adminId = adminId;
		this.adminRestaurantVO = adminRestaurantVO;
		this.adminVO = adminVO;
	}
	
	public AdminAdVO() {
		
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

	public AdminRestaurantVO getAdminRestaurantVO() {
		return adminRestaurantVO;
	}

	public void setAdminRestaurantVO(AdminRestaurantVO adminRestaurantVO) {
		this.adminRestaurantVO = adminRestaurantVO;
	}

	public AdminVO getAdminVO() {
		return adminVO;
	}

	public void setAdminVO(AdminVO adminVO) {
		this.adminVO = adminVO;
	}

}
