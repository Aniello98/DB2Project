package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name = "Order", schema = "db2project")
@NamedQuery(name = "Order.findRejected", query = "SELECT o FROM Order o  WHERE o.user = ?1 AND o.rejected = true")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date creationDate;
	
	private float totalValue;
	
	private float productsValue;
	
	private Date startDate;
	
	private boolean valid;
	
	private boolean rejected;
	
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="OrderedProduct",
	joinColumns = @JoinColumn(name="orderId"),
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

	public int getId() {
		return this.id;
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

	public void setStartDate(Date date) {
		this.startDate = date;
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
	
	public Collection<OptionalProduct> getOptionalProducts() {
		return optionalProducts;
	}

	public void setOptionalProducts(Collection<OptionalProduct> optionalProducts) {
		this.optionalProducts = optionalProducts;
	}
	
	public ValidityPeriod getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(ValidityPeriod validityPeriod) {
		this.validityPeriod = validityPeriod;
	}
	public ServicePackage getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public float getProductsValue() {
		return productsValue;
	}

	public void setProductsValue(float productsValue) {
		this.productsValue = productsValue;
	}
	
}
