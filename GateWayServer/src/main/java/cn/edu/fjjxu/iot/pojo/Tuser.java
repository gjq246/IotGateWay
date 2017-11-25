package cn.edu.fjjxu.iot.pojo;

import java.sql.Timestamp;

/**
 * Tuser entity. @author MyEclipse Persistence Tools
 */

public class Tuser implements java.io.Serializable {

	// Fields

	private Long id;
	private String username;
	private String password;
	private String remark;
	private String truename;
	private String telephone;
	private String address;
	private Timestamp addtime;
	private Integer userenabled;
	private String usertype;

	// Constructors

	/** default constructor */
	public Tuser() {
	}

	/** minimal constructor */
	public Tuser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/** full constructor */
	public Tuser(String username, String password, String remark,
			String truename, String telephone, String address,
			Timestamp addtime, Integer userenabled, String usertype) {
		this.username = username;
		this.password = password;
		this.remark = remark;
		this.truename = truename;
		this.telephone = telephone;
		this.address = address;
		this.addtime = addtime;
		this.userenabled = userenabled;
		this.usertype = usertype;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTruename() {
		return this.truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public Integer getUserenabled() {
		return this.userenabled;
	}

	public void setUserenabled(Integer userenabled) {
		this.userenabled = userenabled;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

}