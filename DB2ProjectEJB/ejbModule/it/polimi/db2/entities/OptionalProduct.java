package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entity implementation class for Entity: OptionalProduct
 *
 */
@Entity
@Table(name = "OptionalProduct", schema = "db2project")
@NamedQuery(name = "OptionalProduct.findAll", query = "SELECT op FROM OptionalProduct op")
@NamedQuery(name = "OptionalProduct.findByName", query = "SELECT op FROM OptionalProduct op WHERE op.name=?1")

public class OptionalProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private float monthlyFee;
	
	//@ManyToMany(mappedBy="optionalProducts")
	//private Collection<Order> orders;
	
	//@ManyToMany(mappedBy="optionalProducts")
	//private Collection<ServicePackage> servicePackages;

	public OptionalProduct() {
		super();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getMonthlyFee() {
		return monthlyFee;
	}


	public void setMonthlyFee(float monthlyFee) {
		this.monthlyFee = monthlyFee;
	}
   
}
