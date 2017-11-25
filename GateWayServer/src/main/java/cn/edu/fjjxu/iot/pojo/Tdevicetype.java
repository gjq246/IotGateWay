package cn.edu.fjjxu.iot.pojo;

import java.sql.Timestamp;

/**
 * Tdevicetype entity. @author MyEclipse Persistence Tools
 */

public class Tdevicetype implements java.io.Serializable {

	// Fields

	private Long id;
	private String devicetypecode;
	private String devicetypename;
	private String devicetypeconfig;
	private String remark;
	private Timestamp addtime;

	// Constructors

	/** default constructor */
	public Tdevicetype() {
	}

	/** minimal constructor */
	public Tdevicetype(String devicetypecode, String devicetypename) {
		this.devicetypecode = devicetypecode;
		this.devicetypename = devicetypename;
	}

	/** full constructor */
	public Tdevicetype(String devicetypecode, String devicetypename,
			String devicetypeconfig, String remark, Timestamp addtime) {
		this.devicetypecode = devicetypecode;
		this.devicetypename = devicetypename;
		this.devicetypeconfig = devicetypeconfig;
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

	public String getDevicetypecode() {
		return this.devicetypecode;
	}

	public void setDevicetypecode(String devicetypecode) {
		this.devicetypecode = devicetypecode;
	}

	public String getDevicetypename() {
		return this.devicetypename;
	}

	public void setDevicetypename(String devicetypename) {
		this.devicetypename = devicetypename;
	}

	public String getDevicetypeconfig() {
		return this.devicetypeconfig;
	}

	public void setDevicetypeconfig(String devicetypeconfig) {
		this.devicetypeconfig = devicetypeconfig;
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