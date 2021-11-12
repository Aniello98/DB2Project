package it.polimi.db2.entities;

import java.io.Serializable;
import javax.persistence.*;



/**
 * Entity implementation class for Entity: ValidityPeriod
 *
 */
@Entity
@Table(name = "validityPeriod", schema = "db2project")


public class ValidityPeriod implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int months;
	
	private float monthlyFee;
	
	//@OneToMany
	//private List<Order> orders;
	
	//@ManyToMany
	//private Collection<ServicePackage> packages;
	
	
	
	public ValidityPeriod() {
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public float getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(float monthlyFee) {
		this.monthlyFee = monthlyFee;
	}
   
}
