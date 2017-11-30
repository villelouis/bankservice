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
import javax.persistence.*;

/**
 *
 * @author Александр
 */
@Entity
@Table(name="Devices", uniqueConstraints={@UniqueConstraint(columnNames={"phoneNum"})})
public class Devices implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long		id;
	@Column(name="phoneNum", nullable = false)
	private Long		phoneNum;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private Users		user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
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
		if (!(object instanceof Devices)) {
			return false;
		}
		Devices other = (Devices) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.Devices[ id=" + id + " ]";
	}
	
}
