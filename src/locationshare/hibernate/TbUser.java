package locationshare.hibernate;

// Generated 2013-7-10 15:57:06 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * TbUser generated by hbm2java
 */
public class TbUser implements java.io.Serializable {

	private Integer userid;
	private String username;
	private String password;
	private String qq;
	private String sina;
	private Date registertime;
	private String registerip;
	private String registerdeviceno;
	private String registeros;

	public TbUser() {
	}

	public TbUser(Date registertime, String registerip,
			String registerdeviceno, String registeros) {
		this.registertime = registertime;
		this.registerip = registerip;
		this.registerdeviceno = registerdeviceno;
		this.registeros = registeros;
	}

	public TbUser(String username, String password, String qq, String sina,
			Date registertime, String registerip, String registerdeviceno,
			String registeros) {
		this.username = username;
		this.password = password;
		this.qq = qq;
		this.sina = sina;
		this.registertime = registertime;
		this.registerip = registerip;
		this.registerdeviceno = registerdeviceno;
		this.registeros = registeros;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSina() {
		return this.sina;
	}

	public void setSina(String sina) {
		this.sina = sina;
	}

	public Date getRegistertime() {
		return this.registertime;
	}

	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}

	public String getRegisterip() {
		return this.registerip;
	}

	public void setRegisterip(String registerip) {
		this.registerip = registerip;
	}

	public String getRegisterdeviceno() {
		return this.registerdeviceno;
	}

	public void setRegisterdeviceno(String registerdeviceno) {
		this.registerdeviceno = registerdeviceno;
	}

	public String getRegisteros() {
		return this.registeros;
	}

	public void setRegisteros(String registeros) {
		this.registeros = registeros;
	}

}
