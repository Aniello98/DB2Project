package it.polimi.db2.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name = "Order", schema = "db2project")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date creationDate;
	
	private float totalValue;
	
	private Date startDate;
	
	private boolean valid;
	
	private boolean rejected;
	
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="OrderedProduct",
	joinColumns = @JoinColumn(name="order"),
	inverseJoinColumns = @JoinColumn(name="optionalProduct"))
	private Collection<OptionalProduct> optionalProducts;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "validityPeriod" )
	private ValidityPeriod validityPeriod;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "servicePackage")
	private ServicePackage servicePackage;
	
	
	public Order() {
		super();
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public float getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}
   
}
