package chooeat.prod.model.vo;

import java.io.Serializable;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountVo implements Serializable{

	private Integer acc_id;
    private String acc_acc;
    private String acc_pass;
    private String acc_name;
    private String acc_nickname;
    private String acc_phone;
    private String acc_mail;
    private String acc_add1;
    private String acc_add2;
    private String acc_add3;
    private Date acc_birth;
    private Integer acc_gender;
    private byte[] acc_pic;
    private String acc_text;
    private Timestamp acc_timestamp;
    private Integer acc_state;
    private Integer online_status;
    
    public  AccountVo() {
		
	}
    

	public AccountVo(Integer acc_id, String acc_acc, String acc_pass, String acc_name, String acc_nickname,
			String acc_phone, String acc_mail, String acc_add1, String acc_add2, String acc_add3, Date acc_birth,
			Integer acc_gender, byte[] acc_pic, String acc_text, Timestamp acc_timestamp, Integer acc_state,
			Integer online_status) {
		
		this.acc_id = acc_id;
		this.acc_acc = acc_acc;
		this.acc_pass = acc_pass;
		this.acc_name = acc_name;
		this.acc_nickname = acc_nickname;
		this.acc_phone = acc_phone;
		this.acc_mail = acc_mail;
		this.acc_add1 = acc_add1;
		this.acc_add2 = acc_add2;
		this.acc_add3 = acc_add3;
		this.acc_birth = acc_birth;
		this.acc_gender = acc_gender;
		this.acc_pic = acc_pic;
		this.acc_text = acc_text;
		this.acc_timestamp = acc_timestamp;
		this.acc_state =acc_state;
		this.online_status = online_status;
	}

	public Integer getAcc_id() {
		return acc_id;
	}

	public void setAcc_id(Integer acc_id) {
		this.acc_id = acc_id;
	}

	public String getAcc_acc() {
		return acc_acc;
	}

	public void setAcc_acc(String acc_acc) {
		this.acc_acc = acc_acc;
	}

	public String getAcc_pass() {
		return acc_pass;
	}

	public void setAcc_pass(String acc_pass) {
		this.acc_pass = acc_pass;
	}

	public String getAcc_name() {
		return acc_name;
	}

	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}

	public String getAcc_nickname() {
		return acc_nickname;
	}

	public void setAcc_nickname(String acc_nickname) {
		this.acc_nickname = acc_nickname;
	}

	public String getAcc_phone() {
		return acc_phone;
	}

	public void setAcc_phone(String acc_phone) {
		this.acc_phone = acc_phone;
	}

	public String getAcc_mail() {
		return acc_mail;
	}

	public void setAcc_mail(String acc_mail) {
		this.acc_mail = acc_mail;
	}

	public String getAcc_add1() {
		return acc_add1;
	}

	public void setAcc_add1(String acc_add1) {
		this.acc_add1 = acc_add1;
	}

	public String getAcc_add2() {
		return acc_add2;
	}

	public void setAcc_add2(String acc_add2) {
		this.acc_add2 = acc_add2;
	}

	public String getAcc_add3() {
		return acc_add3;
	}

	public void setAcc_add3(String acc_add3) {
		this.acc_add3 = acc_add3;
	}

	public Date getAcc_birth() {
		return acc_birth;
	}

	public void setAcc_birth(Date acc_birth) {
		this.acc_birth = acc_birth;
	}

	 public Integer getAcc_gender() {
	        return acc_gender;
	    }

	    public void setAcc_gender(Integer acc_gender) {
	        this.acc_gender = acc_gender;
	    }

	    // 修改setAcc_gender()方法，接受String类型的参数，并在内部进行转换
	    public void setAcc_gender(String acc_gender) {
	        // 根据具体需求进行转换，例如：
	        if (acc_gender.equals("1")) {
	            this.acc_gender = 1;
	        } else if (acc_gender.equals("2")) {
	            this.acc_gender = 2;
	        } else {
	            this.acc_gender = 3;
	        }
	    }

	public byte[] getAcc_pic() {
		return acc_pic;
	}

	public void setAcc_pic(byte[] acc_pic) {
		this.acc_pic = acc_pic;
	}

	public String getAcc_text() {
		return acc_text;
	}

	public void setAcc_text(String acc_text) {
		this.acc_text = acc_text;
	}

	public Timestamp getAcc_timestamp() {
		return acc_timestamp;
	}

	public void setAcc_timestamp(Timestamp acc_timestamp) {
		this.acc_timestamp = acc_timestamp;
	}

	public Integer getAcc_state() {
	    return acc_state != null ? acc_state : 0;
	}

	public void setAcc_state(Integer acc_state) {
		this.acc_state = acc_state;
	}

	public Integer getOnline_status() {
		 return online_status != null ? online_status : 0;
	}

	public void setOnline_status(Integer online_status) {
		this.online_status = online_status;
	}
	public void setAcc_birth(String regbirth) {
	    // 根据日期格式进行解析和转换，例如：
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        Date parsedDate = (Date) dateFormat.parse(regbirth);
	        this.acc_birth = (Date) new java.util.Date(parsedDate.getTime());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	}	
	

}


