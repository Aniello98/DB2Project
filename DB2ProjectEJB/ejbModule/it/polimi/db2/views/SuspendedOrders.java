package it.polimi.db2.views;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SuspendedOrders
 *
 */
@Entity
@Table(name = "SuspendedOrders", schema = "db2project")
@NamedQuery(name = "SuspendedOrders.findAll", query = "SELECT pp FROM SuspendedOrders pp")

public class SuspendedOrders implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int orderId;
	
	private int userId;
	

	public SuspendedOrders() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
   
}
