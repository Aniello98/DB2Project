package it.polimi.db2.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Alert
 *
 */
@Entity
@Table(name = "Alert", schema = "db2project")
@NamedQuery(name = "Alert.findAll", query = "SELECT pp FROM Alert pp")
public class Alert implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int user;
	
	private int amount;
	
	private Date lastRejection;
	
	public Alert() {
		super();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getLastRejection() {
		return lastRejection;
	}

	public void setLastRejection(Date lastRejection) {
		this.lastRejection = lastRejection;
	}
   
}
