package chooeat.admin.web.resType.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import chooeat.admin.core.pojo.Core;

@Entity
@Table(name = "res_type")
public class ResTypeVO extends Core{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "res_type_id", updatable = false)
	private Integer resTypeId;
	
	@Column(name = "res_type_name")
	private String resTypeName;
	
	private Integer resTypeCount;
	
	public ResTypeVO(Integer resTypeId, String resTypeName, Integer resTypeCount) {
		this.resTypeId = resTypeId;
		this.resTypeName = resTypeName;
		this.resTypeCount = resTypeCount;
	}
	
	public ResTypeVO() {
		
	}
	
	public Integer getResTypeId() {
		return resTypeId;
	}
	
	public void setResTypeId(Integer resTypeId) {
		this.resTypeId = resTypeId;
	}
	
	public String getResTypeName() {
		return resTypeName;
	}
	
	public void setResTypeName(String resTypeName) {
		this.resTypeName = resTypeName;
	}

	public Integer getResTypeCount() {
		return resTypeCount;
	}

	public void setResTypeCount(Integer resTypeCount) {
		this.resTypeCount = resTypeCount;
	}
	
}
