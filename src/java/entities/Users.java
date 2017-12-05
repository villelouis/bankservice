/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;
import javax.persistence.OneToMany;
import javax.persistence.*;

/**
 *
 * @author Александр
 */
@Entity @Table(name="Users", uniqueConstraints={@UniqueConstraint(columnNames={"login"})})
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long			id;
	@Column(name="login", nullable=false)
	private String			login;
	@Column(nullable = false)
	private String			password;
	private Long			money;
	@OneToMany(mappedBy = "user", cascade = javax.persistence.CascadeType.REMOVE)
	private List<Devices>	devices;
	private String			sertificate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMoney() {
		return money;
	}
	
	public void setMoney(Long money) {
		this.money = money;
	}

	public List<Devices> getDevices() {
		return devices;
	}

	public void setDevices(List<Devices> devices) {
		this.devices = devices;
	}

	public String getSertificate() {
		return sertificate;
	}

	public void setSertificate(String sertificate) {
		this.sertificate = sertificate;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Users)) {
			return false;
		}
		Users other = (Users) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.Users[ id=" + id + " ]";
	}
	
}
