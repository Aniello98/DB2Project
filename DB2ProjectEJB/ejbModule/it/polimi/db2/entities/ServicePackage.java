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
@Table(name = "servicePackage", schema = "db2project")
public class ServicePackage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="serviceValidity",
	joinColumns = @JoinColumn(name="servicePackage"),
	inverseJoinColumns = @JoinColumn(name="validityPeriod"))
	private Collection<ValidityPeriod> validityPeriods;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="offeredProducts",
	joinColumns = @JoinColumn(name="servicePackage"),
	inverseJoinColumns = @JoinColumn(name="optionalProduct"))
	private Collection<OptionalProduct> optionalProducts;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="offeredServices",
	joinColumns = @JoinColumn(name="servicePackage"),
	inverseJoinColumns = @JoinColumn(name="service"))
	private Collection<Service> services;
	
	@OneToMany(mappedBy="servicePackage")
	private List<Order> orders;
	
	
	public ServicePackage() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
