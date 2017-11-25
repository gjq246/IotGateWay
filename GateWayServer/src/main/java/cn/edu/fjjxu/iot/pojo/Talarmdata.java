package cn.edu.fjjxu.iot.pojo;

import java.sql.Timestamp;

/**
 * Talarmdata entity. @author MyEclipse Persistence Tools
 */

public class Talarmdata implements java.io.Serializable {

	// Fields

	private Long id;
	private Long gdid;
	private Timestamp alarmtime;
	private String alarmdata;
	private String pushresult;
	private String proccessresult;
	private String remark;

	// Constructors

	/** default constructor */
	public Talarmdata() {
	}

	/** minimal constructor */
	public Talarmdata(Timestamp alarmtime, String alarmdata) {
		this.alarmtime = alarmtime;
		this.alarmdata = alarmdata;
	}

	/** full constructor */
	public Talarmdata(Long gdid, Timestamp alarmtime, String alarmdata,
			String pushresult, String proccessresult, String remark) {
		this.gdid = gdid;
		this.alarmtime = alarmtime;
		this.alarmdata = alarmdata;
		this.pushresult = pushresult;
		this.proccessresult = proccessresult;
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

	public Timestamp getAlarmtime() {
		return this.alarmtime;
	}

	public void setAlarmtime(Timestamp alarmtime) {
		this.alarmtime = alarmtime;
	}

	public String getAlarmdata() {
		return this.alarmdata;
	}

	public void setAlarmdata(String alarmdata) {
		this.alarmdata = alarmdata;
	}

	public String getPushresult() {
		return this.pushresult;
	}

	public void setPushresult(String pushresult) {
		this.pushresult = pushresult;
	}

	public String getProccessresult() {
		return this.proccessresult;
	}

	public void setProccessresult(String proccessresult) {
		this.proccessresult = proccessresult;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}