package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Collection;

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

	@ManyToMany
	private Collection<ValidityPeriod> validityPeriods;
	
	@ManyToMany
	private Collection<OptionalProduct> optionalProducts;
	
	
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
