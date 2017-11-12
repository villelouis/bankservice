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
@Entity @Table(name="Users")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long			id;
	private String			login;
	private String			password;
	private Long			money;
	@OneToMany
	private Set<Devices>	devices = new HashSet<>();

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

	public Set<Devices> getDevices() {
		return devices;
	}
	
	public void setDevices(Set<Devices> devices) {
		this.devices = devices;
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
