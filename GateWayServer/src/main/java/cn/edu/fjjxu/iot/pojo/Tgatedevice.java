package cn.edu.fjjxu.iot.pojo;

import java.sql.Timestamp;

/**
 * Tgatedevice entity. @author MyEclipse Persistence Tools
 */

public class Tgatedevice implements java.io.Serializable {

	// Fields

	private Long id;
	private Long gid;
	private Long did;
	private String clientdevicename;
	private String clientdeviceid;
	private String clientdeviceconfig;
	private String clientdevicestate;
	private Integer clientdeviceenabled;
	private String remark;
	private Timestamp addtime;

	// Constructors

	/** default constructor */
	public Tgatedevice() {
	}

	/** minimal constructor */
	public Tgatedevice(String clientdeviceid) {
		this.clientdeviceid = clientdeviceid;
	}

	/** full constructor */
	public Tgatedevice(Long gid, Long did, String clientdevicename,
			String clientdeviceid, String clientdeviceconfig,
			String clientdevicestate, Integer clientdeviceenabled,
			String remark, Timestamp addtime) {
		this.gid = gid;
		this.did = did;
		this.clientdevicename = clientdevicename;
		this.clientdeviceid = clientdeviceid;
		this.clientdeviceconfig = clientdeviceconfig;
		this.clientdevicestate = clientdevicestate;
		this.clientdeviceenabled = clientdeviceenabled;
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

	public Long getGid() {
		return this.gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public Long getDid() {
		return this.did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public String getClientdevicename() {
		return this.clientdevicename;
	}

	public void setClientdevicename(String clientdevicename) {
		this.clientdevicename = clientdevicename;
	}

	public String getClientdeviceid() {
		return this.clientdeviceid;
	}

	public void setClientdeviceid(String clientdeviceid) {
		this.clientdeviceid = clientdeviceid;
	}

	public String getClientdeviceconfig() {
		return this.clientdeviceconfig;
	}

	public void setClientdeviceconfig(String clientdeviceconfig) {
		this.clientdeviceconfig = clientdeviceconfig;
	}

	public String getClientdevicestate() {
		return this.clientdevicestate;
	}

	public void setClientdevicestate(String clientdevicestate) {
		this.clientdevicestate = clientdevicestate;
	}

	public Integer getClientdeviceenabled() {
		return this.clientdeviceenabled;
	}

	public void setClientdeviceenabled(Integer clientdeviceenabled) {
		this.clientdeviceenabled = clientdeviceenabled;
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