package it.polimi.db2.views;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PurchasesPerPackage
 *
 */
@Entity
@Table(name = "PurchasesPerPackage", schema = "db2project")
@NamedQuery(name = "PurchasesPerPackage.findAll", query = "SELECT pp FROM PurchasesPerPackage pp")
public class PurchasesPerPackage implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public PurchasesPerPackage() {
		super();
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

	@Id
	private int packageId;
	
	private String packageName;
	
	private int amount;
   
}
