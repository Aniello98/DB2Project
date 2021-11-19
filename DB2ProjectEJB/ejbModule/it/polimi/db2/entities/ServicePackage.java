package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: ServicePackage
 *
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "ServicePackage", schema = "db2project")
@NamedQuery(name = "ServicePackage.findAll", query="SELECT p FROM ServicePackage p")
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
