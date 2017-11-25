package cn.edu.fjjxu.iot.pojo;

import java.sql.Timestamp;

/**
 * Tgate entity. @author MyEclipse Persistence Tools
 */

public class Tgate implements java.io.Serializable {

	// Fields

	private Long id;
	private Long pid;
	private String gatename;
	private String gateid;
	private Integer gateenabled;
	private String remark;
	private Timestamp addtime;

	// Constructors

	/** default constructor */
	public Tgate() {
	}

	/** minimal constructor */
	public Tgate(String gatename, String gateid) {
		this.gatename = gatename;
		this.gateid = gateid;
	}

	/** full constructor */
	public Tgate(Long pid, String gatename, String gateid, Integer gateenabled,
			String remark, Timestamp addtime) {
		this.pid = pid;
		this.gatename = gatename;
		this.gateid = gateid;
		this.gateenabled = gateenabled;
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

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getGatename() {
		return this.gatename;
	}

	public void setGatename(String gatename) {
		this.gatename = gatename;
	}

	public String getGateid() {
		return this.gateid;
	}

	public void setGateid(String gateid) {
		this.gateid = gateid;
	}

	public Integer getGateenabled() {
		return this.gateenabled;
	}

	public void setGateenabled(Integer gateenabled) {
		this.gateenabled = gateenabled;
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