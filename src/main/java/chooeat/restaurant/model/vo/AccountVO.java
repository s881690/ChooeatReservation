package chooeat.restaurant.model.vo;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class AccountVO implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Integer accId;
	    private String accAcc;
	    private String accPass;
	    private String accName;
	    private String accNickname;
	    private String accPhone;
	    private String accMail;
	    private String accAdd1;
	    private String accAdd2;
	    private String accAdd3;
	    private Time accBirth;
	    private Integer accGender;
	    private Byte[] accPic;
	    private String accText;
	    private Timestamp accTimestamp;
	    private Integer accState;
	    private Integer onlineStatus;
	    
	    public AccountVO() {
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

		public Time getAccBirth() {
			return accBirth;
		}

		public void setAccBirth(Time accBirth) {
			this.accBirth = accBirth;
		}

		public Integer getAccGender() {
			return accGender;
		}

		public void setAccGender(Integer accGender) {
			this.accGender = accGender;
		}

		public Byte[] getAccPic() {
			return accPic;
		}

		public void setAccPic(Byte[] accPic) {
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
