package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ServicePackage
 *
 */
@Entity
@Table(name = "ServicePackage", schema = "db2project")
@NamedQuery(name = "ServicePackage.findAll", query="SELECT p FROM ServicePackage p")
@NamedQuery(name = "ServicePackage.findByName", query="SELECT p FROM ServicePackage p WHERE p.name=?1")
public class ServicePackage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="ServiceValidity",
	joinColumns = @JoinColumn(name="servicePackage"),
	inverseJoinColumns = @JoinColumn(name="validityPeriod"))
	private Collection<ValidityPeriod> validityPeriods;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="OfferedProduct",
	joinColumns = @JoinColumn(name="servicePackage"),
	inverseJoinColumns = @JoinColumn(name="optionalProduct"))
	private Collection<OptionalProduct> optionalProducts;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="OfferedService",
	joinColumns = @JoinColumn(name="servicePackage"),
	inverseJoinColumns = @JoinColumn(name="service"))
	private Collection<Service> services;
	
	//@OneToMany(mappedBy="servicePackage")
	//private List<Order> orders;
	
	
	public ServicePackage() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setValidityPeriod(List<ValidityPeriod> periods) {
		this.validityPeriods = periods;
	}
	
	public void setOptionalProducts(List<OptionalProduct> products) {
		this.optionalProducts = products;
	}
	
	public void setServices(List<Service> services) {
		this.services = services;
	}
   
}
