package cn.edu.fjjxu.iot.pojo;

import java.sql.Timestamp;

/**
 * Thistorydata entity. @author MyEclipse Persistence Tools
 */

public class Thistorydata implements java.io.Serializable {

	// Fields

	private Long id;
	private Long gdid;
	private Timestamp recordtime;
	private String recorddata;
	private String remark;

	// Constructors

	/** default constructor */
	public Thistorydata() {
	}

	/** minimal constructor */
	public Thistorydata(Timestamp recordtime, String recorddata) {
		this.recordtime = recordtime;
		this.recorddata = recorddata;
	}

	/** full constructor */
	public Thistorydata(Long gdid, Timestamp recordtime, String recorddata,
			String remark) {
		this.gdid = gdid;
		this.recordtime = recordtime;
		this.recorddata = recorddata;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGdid() {
		return this.gdid;
	}

	public void setGdid(Long gdid) {
		this.gdid = gdid;
	}

	public Timestamp getRecordtime() {
		return this.recordtime;
	}

	public void setRecordtime(Timestamp recordtime) {
		this.recordtime = recordtime;
	}

	public String getRecorddata() {
		return this.recorddata;
	}

	public void setRecorddata(String recorddata) {
		this.recorddata = recorddata;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}