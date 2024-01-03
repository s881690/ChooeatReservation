package chooeat.restaurant.model.vo;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class RestaurantVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2099521162026557829L;
	private Integer restaurantId; // 餐廳流水編號（主鍵）
	private String resAcc; // 餐廳會員帳號
	private String resPass; // 餐廳會員密碼
	private Integer resState; // 餐廳帳號狀態
	private String resName; // 餐廳名稱
	private String resAdd; // 餐廳地址
	private String resTel; // 餐廳電話
	private String resEmail; // 餐廳電子信箱
	private String resWeb; // 餐廳連結
	private Timestamp resTimestamp; // 註冊日期
	private Time resStartTime; // 開始營業時間
	private Time resEndTime; // 結束營業時間
	private String resTexId; // 統一編號
	private String resOwnerName; // 餐廳負責人姓名
	private Integer resSeatNumber; // 座位總數
	private String resIntro; // 餐廳簡介
	private Boolean singleMeal; // 是否接受單一客人
	private Integer resTotalScore; // 餐廳評論總分
	private Integer resTotalNumber; // 餐廳評論總人數
	private Integer resMaxNum; // 每時段可訂位座位上限
    private Byte[] resPhoto;// 餐廳照片
        
	public RestaurantVO() {

	}
	
	public Byte[] getResPhoto() {
		return resPhoto;
	}

	public void setResPhoto(Byte[] resPhoto) {
		this.resPhoto = resPhoto;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getResAcc() {
		return resAcc;
	}

	public void setResAcc(String resAcc) {
		this.resAcc = resAcc;
	}

	public String getResPass() {
		return resPass;
	}

	public void setResPass(String resPass) {
		this.resPass = resPass;
	}

	public Integer getResState() {
		return resState;
	}

	public void setResState(Integer resState) {
		this.resState = resState;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResAdd() {
		return resAdd;
	}

	public void setResAdd(String resAdd) {
		this.resAdd = resAdd;
	}

	public String getResTel() {
		return resTel;
	}

	public void setResTel(String resTel) {
		this.resTel = resTel;
	}

	public String getResEmail() {
		return resEmail;
	}

	public void setResEmail(String resEmail) {
		this.resEmail = resEmail;
	}

	public String getResWeb() {
		return resWeb;
	}

	public void setResWeb(String resWeb) {
		this.resWeb = resWeb;
	}

	public Timestamp getResTimestamp() {
		return resTimestamp;
	}

	public void setResTimestamp(Timestamp resTimestamp) {
		this.resTimestamp = resTimestamp;
	}

	public Time getResStartTime() {
		return resStartTime;
	}

	public void setResStartTime(Time resStartTime) {
		this.resStartTime = resStartTime;
	}

	public Time getResEndTime() {
		return resEndTime;
	}

	public void setResEndTime(Time resEndTime) {
		this.resEndTime = resEndTime;
	}

	public String getResTexId() {
		return resTexId;
	}

	public void setResTexId(String resTexId) {
		this.resTexId = resTexId;
	}

	public String getResOwnerName() {
		return resOwnerName;
	}

	public void setResOwnerName(String resOwnerName) {
		this.resOwnerName = resOwnerName;
	}

	public Integer getResSeatNumber() {
		return resSeatNumber;
	}

	public void setResSeatNumber(Integer resSeatNumber) {
		this.resSeatNumber = resSeatNumber;
	}

	public String getResIntro() {
		return resIntro;
	}

	public void setResIntro(String resIntro) {
		this.resIntro = resIntro;
	}

	public Boolean getSingleMeal() {
		return singleMeal;
	}

	public void setSingleMeal(Boolean singleMeal) {
		this.singleMeal = singleMeal;
	}

	public Integer getResTotalScore() {
		return resTotalScore;
	}

	public void setResTotalScore(Integer resTotalScore) {
		this.resTotalScore = resTotalScore;
	}

	public Integer getResTotalNumber() {
		return resTotalNumber;
	}

	public void setResTotalNumber(Integer resTotalNumber) {
		this.resTotalNumber = resTotalNumber;
	}

	public Integer getResMaxNum() {
		return resMaxNum;
	}

	public void setResMaxNum(Integer resMaxNum) {
		this.resMaxNum = resMaxNum;
	}
}