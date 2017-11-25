package cn.edu.fjjxu.iot.pojo;

import java.sql.Timestamp;

/**
 * Tdevice entity. @author MyEclipse Persistence Tools
 */

public class Tdevice implements java.io.Serializable {

	// Fields

	private Long id;
	private Long dtid;
	private String devicecode;
	private String devicename;
	private String devicephoto;
	private String deviceconfig;
	private Integer deviceenabled;
	private String remark;
	private Timestamp addtime;

	// Constructors

	/** default constructor */
	public Tdevice() {
	}

	/** minimal constructor */
	public Tdevice(String devicecode, String devicename) {
		this.devicecode = devicecode;
		this.devicename = devicename;
	}

	/** full constructor */
	public Tdevice(Long dtid, String devicecode, String devicename,
			String devicephoto, String deviceconfig, Integer deviceenabled,
			String remark, Timestamp addtime) {
		this.dtid = dtid;
		this.devicecode = devicecode;
		this.devicename = devicename;
		this.devicephoto = devicephoto;
		this.deviceconfig = deviceconfig;
		this.deviceenabled = deviceenabled;
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

	public Long getDtid() {
		return this.dtid;
	}

	public void setDtid(Long dtid) {
		this.dtid = dtid;
	}

	public String getDevicecode() {
		return this.devicecode;
	}

	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}

	public String getDevicename() {
		return this.devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getDevicephoto() {
		return this.devicephoto;
	}

	public void setDevicephoto(String devicephoto) {
		this.devicephoto = devicephoto;
	}

	public String getDeviceconfig() {
		return this.deviceconfig;
	}

	public void setDeviceconfig(String deviceconfig) {
		this.deviceconfig = deviceconfig;
	}

	public Integer getDeviceenabled() {
		return this.deviceenabled;
	}

	public void setDeviceenabled(Integer deviceenabled) {
		this.deviceenabled = deviceenabled;
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