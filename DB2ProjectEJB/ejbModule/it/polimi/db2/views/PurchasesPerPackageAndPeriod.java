package it.polimi.db2.views;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PurchasesPerPackageAndPeriod
 *
 */
@Entity
@IdClass(PurchasesPerPackageAndPeriodId.class)
@Table(name = "PurchasesPerPackageAndPeriod", schema = "db2project")
@NamedQuery(name = "PurchasesPerPackageAndPeriod.findAll", query = "SELECT pp FROM PurchasesPerPackageAndPeriod pp")
public class PurchasesPerPackageAndPeriod implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public PurchasesPerPackageAndPeriod() {
		super();
	}
	
	public int getPackageId() {
		return packageId;
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}
	
	

	public float getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(float monthlyFee) {
		this.monthlyFee = monthlyFee;
	}



	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}



	@Id
	private int packageId;
	
	@Id
	private int periodId;
	
	private String packageName;
	
	private int months;
	
	private float monthlyFee;
	
	private int amount;
   
}
