package chooeat.admin.web.admin.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import chooeat.admin.core.pojo.Core;

@Entity
@Table(name = "admin")
public class AdminVO extends Core{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id", updatable = false)
    private Integer adminId;
	
	@Column(name = "admin_acc")
    private String adminAcc;
	
	@Column(name = "admin_name")
    private String adminName;
	
	@Column(name = "admin_pass")
    private String adminPass;
	
	@Column(name = "admin_timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp adminTimestamp;
	
	@Column(name = "admin_permission")
    private Integer adminPermission;

    // Constructor
    public AdminVO(int adminId, String adminAcc, String adminName, String adminPass, Timestamp adminTimestamp, int adminPermission) {
        this.adminId = adminId;
        this.adminAcc = adminAcc;
        this.adminName = adminName;
        this.adminPass = adminPass;
        this.adminTimestamp = adminTimestamp;
        this.adminPermission = adminPermission;
    }
    
    public AdminVO() {
    	
    }

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminAcc() {
		return adminAcc;
	}

	public void setAdminAcc(String adminAcc) {
		this.adminAcc = adminAcc;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	public Timestamp getAdminTimestamp() {
		return adminTimestamp;
	}

	public void setAdminTimestamp(Timestamp adminTimestamp) {
		this.adminTimestamp = adminTimestamp;
	}

	public Integer getAdminPermission() {
		return adminPermission;
	}

	public void setAdminPermission(int adminPermission) {
		this.adminPermission = adminPermission;
	}
    
    
}
