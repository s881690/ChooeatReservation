package chooeat.admin.web.acc.pojo;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import chooeat.admin.core.pojo.Core;

@Entity
@Table(name = "account")
public class AdminAccountVO extends Core{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acc_id", updatable = false)
	private Integer accId;
	
	@Column(name = "acc_acc")
    private String accAcc;
	
	@Column(name = "acc_pass")
    private String accPass;
	
	@Column(name = "acc_name")
    private String accName;
	
	@Column(name = "acc_nickname")
    private String accNickname;
	
	@Column(name = "acc_phone")
    private String accPhone;
	
	@Column(name = "acc_mail")
    private String accMail;
	
	@Column(name = "acc_add1")
    private String accAdd1;
	
	@Column(name = "acc_add2")
	private String accAdd2;
	
	@Column(name = "acc_add3")
    private String accAdd3;
	
	@Column(name = "acc_birth")
    private Date accBirth;
	
	@Column(name = "acc_gender")
    private Integer accGender;
	
	@Lob
	@Column(name = "acc_pic")
    private byte[] accPic;
	
	@Column(name = "acc_text")
    private String accText;
	
	@Column(name = "acc_timestamp")
    private Timestamp accTimestamp;
	
	@Column(name = "acc_state")
    private Integer accState;
	
	@Column(name = "online_status")
    private Integer onlineStatus;
    
	public AdminAccountVO(Integer accId, String accAcc, String accPass, String accName, String accNickname, String accPhone,
			String accMail, String accAdd1, String accAdd2, String accAdd3, Date accBirth, Integer accGender,
			byte[] accPic, String accText, Timestamp accTimestamp, Integer accState, Integer onlineStatus) {
		this.accId = accId;
		this.accAcc = accAcc;
		this.accPass = accPass;
		this.accName = accName;
		this.accNickname = accNickname;
		this.accPhone = accPhone;
		this.accMail = accMail;
		this.accAdd1 = accAdd1;
		this.accAdd2 = accAdd2;
		this.accAdd3 = accAdd3;
		this.accBirth = accBirth;
		this.accGender = accGender;
		this.accPic = accPic;
		this.accText = accText;
		this.accTimestamp = accTimestamp;
		this.accState = accState;
		this.onlineStatus = onlineStatus;
	}
	
	public AdminAccountVO () {
		
	}
	
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public String getAccAcc() {
		return accAcc;
	}
	public void setAccAcc(String accAcc) {
		this.accAcc = accAcc;
	}
	public String getAccPass() {
		return accPass;
	}
	public void setAccPass(String accPass) {
		this.accPass = accPass;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getAccNickname() {
		return accNickname;
	}
	public void setAccNickname(String accNickname) {
		this.accNickname = accNickname;
	}
	public String getAccPhone() {
		return accPhone;
	}
	public void setAccPhone(String accPhone) {
		this.accPhone = accPhone;
	}
	public String getAccMail() {
		return accMail;
	}
	public void setAccMail(String accMail) {
		this.accMail = accMail;
	}
	public String getAccAdd1() {
		return accAdd1;
	}
	public void setAccAdd1(String accAdd1) {
		this.accAdd1 = accAdd1;
	}
	public String getAccAdd2() {
		return accAdd2;
	}
	public void setAccAdd2(String accAdd2) {
		this.accAdd2 = accAdd2;
	}
	public String getAccAdd3() {
		return accAdd3;
	}
	public void setAccAdd3(String accAdd3) {
		this.accAdd3 = accAdd3;
	}
	public Date getAccBirth() {
		return accBirth;
	}
	public void setAccBirth(Date accBirth) {
		this.accBirth = accBirth;
	}
	public Integer getAccGender() {
		return accGender;
	}
	public void setAccGender(Integer accGender) {
		this.accGender = accGender;
	}
	public byte[] getAccPic() {
		return accPic;
	}
	public void setAccPic(byte[] accPic) {
		this.accPic = accPic;
	}
	public String getAccText() {
		return accText;
	}
	public void setAccText(String accText) {
		this.accText = accText;
	}
	public Timestamp getAccTimestamp() {
		return accTimestamp;
	}
	public void setAccTimestamp(Timestamp accTimestamp) {
		this.accTimestamp = accTimestamp;
	}
	public Integer getAccState() {
		return accState;
	}
	public void setAccState(Integer accState) {
		this.accState = accState;
	}
	public Integer getOnlineStatus() {
		return onlineStatus;
	}
	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
    
    
}
