package cn.edu.fjjxu.iot.pojo;

import java.sql.Timestamp;

/**
 * Tproject entity. @author MyEclipse Persistence Tools
 */

public class Tproject implements java.io.Serializable {

	// Fields

	private Long id;
	private Long uid;
	private String projectname;
	private String remark;
	private Timestamp addtime;

	// Constructors

	/** default constructor */
	public Tproject() {
	}

	/** minimal constructor */
	public Tproject(String projectname) {
		this.projectname = projectname;
	}

	/** full constructor */
	public Tproject(Long uid, String projectname, String remark,
			Timestamp addtime) {
		this.uid = uid;
		this.projectname = projectname;
		this.remark = remark;
		this.addtime = addtime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getProjectname() {
		return this.projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}