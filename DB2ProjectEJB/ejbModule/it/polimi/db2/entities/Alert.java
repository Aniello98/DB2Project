package it.polimi.db2.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Alert
 *
 */
@Entity
@Table(name = "Alert", schema = "db2project")

public class Alert implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	private float amount;
	
	private Date lastRejection;
	
	//TODO: revise cascade
	@Id
	@OneToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinColumn(name="user")
	private User user;

	public Alert() {
		
	}
	
	public Alert(float amount, Date lastRejection) {
		this.amount=amount;
		this.lastRejection=lastRejection;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getLastRejection() {
		return lastRejection;
	}

	public void setLastRejection(Date lastRejection) {
		this.lastRejection = lastRejection;
	}
	
	
   
}
