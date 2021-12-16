package it.polimi.db2.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PurchasesPerPackage
 *
 */
@Entity
@Table(name = "ActivationSchedule", schema = "db2project")
@NamedQuery(name = "ActivationSchedule.findByUsername", query = "SELECT ar FROM ActivationSchedule ar WHERE ar.username = ?1")
public class ActivationSchedule implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ActivationSchedule() {
		super();
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Date getActivationDate() {
		return activationDate;
	}


	public void setActivationDate(Date startDate) {
		this.activationDate = startDate;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	@Id
	private int orderId;
	
	private int userId;
	
	private String username;
	
	private Date activationDate;
	
	private Date expirationDate;
   
}