package chooeat.reservation.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "restaurant")
@Component
public class RestaurantVO {

    @Id
    @Column(name = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantId;

    @Column(name = "res_acc")
    private String resAcc;

    @Column(name = "res_pass")
    private String resPass;

    @Column(name = "res_state")
    private Integer resState;

    @Column(name = "res_name")
    private String resName;

    @Column(name = "res_add")
    private String resAdd;

    @Column(name = "res_tel")
    private String resTel;

    @Column(name = "res_email")
    private String resEmail;

    @Column(name = "res_web")
    private String resWeb;

    @Column(name = "res_timestamp")
    private Timestamp resTimestamp;

    @Column(name = "res_start_time")
    private Time resStartTime;

    @Column(name = "res_end_time")
    private Time resEndTime;

    @Column(name = "res_tex_id")
    private String resTexId;

    @Column(name = "res_owner_name")
    private String resOwnerName;

    @Column(name = "res_seat_number")
    private Integer resSeatNumber;

    @Column(name = "res_intro")
    private String resIntro;

    @Column(name = "single_meal")
    private Boolean singleMeal;

    @Column(name = "res_total_score")
    private Integer resTotalScore;

    @Column(name = "res_total_number")
    private Integer resTotalNumber;

    @Column(name = "res_max_num")
    private Integer resMaxNum;

    public RestaurantVO() {
    }


    public RestaurantVO(Integer restaurantId, String resAcc, String resPass, Integer resState, String resName,
                        String resAdd, String resTel, String resEmail, String resWeb, Timestamp resTimestamp, Time resStartTime,
                        Time resEndTime, String resTexId, String resOwnerName, Integer resSeatNumber, String resIntro,
                        Boolean singleMeal, Integer resTotalScore, Integer resTotalNumber, Integer resMaxNum) {
        super();
        this.restaurantId = restaurantId;
        this.resAcc = resAcc;
        this.resPass = resPass;
        this.resState = resState;
        this.resName = resName;
        this.resAdd = resAdd;
        this.resTel = resTel;
        this.resEmail = resEmail;
        this.resWeb = resWeb;
        this.resTimestamp = resTimestamp;
        this.resStartTime = resStartTime;
        this.resEndTime = resEndTime;
        this.resTexId = resTexId;
        this.resOwnerName = resOwnerName;
        this.resSeatNumber = resSeatNumber;
        this.resIntro = resIntro;
        this.singleMeal = singleMeal;
        this.resTotalScore = resTotalScore;
        this.resTotalNumber = resTotalNumber;
        this.resMaxNum = resMaxNum;
    }


    @Override
    public String toString() {
        return "RestaurantVO [restaurantId=" + restaurantId + ", resAcc=" + resAcc + ", resPass=" + resPass
                + ", resState=" + resState + ", resName=" + resName + ", resAdd=" + resAdd + ", resTel=" + resTel
                + ", resEmail=" + resEmail + ", resWeb=" + resWeb + ", resTimestamp=" + resTimestamp
                + ", resStartTime=" + resStartTime + ", resEndTime=" + resEndTime + ", resTexId=" + resTexId
                + ", resOwnerName=" + resOwnerName + ", resSeatNumber=" + resSeatNumber + ", resIntro=" + resIntro
                + ", singleMeal=" + singleMeal + ", resTotalScore=" + resTotalScore + ", resTotalNumber="
                + resTotalNumber + ", resMaxNum=" + resMaxNum + "]";
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


    // 可根據需要自行增加建構子、其他方法、覆寫等

    // 不包含getter和setter方法

}
