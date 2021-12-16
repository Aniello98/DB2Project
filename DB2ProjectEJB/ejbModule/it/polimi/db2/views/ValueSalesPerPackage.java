package it.polimi.db2.views;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ValueSalesPerPackage
 *
 */
@Entity
@Table(name = "ValueSalesPerPackage", schema = "db2project")
@NamedQuery(name = "ValueSalesPerPackage.findAll", query = "SELECT pp FROM ValueSalesPerPackage pp")
public class ValueSalesPerPackage implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	@Id
	private int packageId;
	
	private String packageName;
	
	private float totalWithoutOptional;
	
	private float totalWithOptional;
	
	public ValueSalesPerPackage() {
		super();
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public float getTotalWithoutOptional() {
		return totalWithoutOptional;
	}

	public void setTotalWithoutOptional(float totalWithoutOptional) {
		this.totalWithoutOptional = totalWithoutOptional;
	}

	public float getTotalWithOptional() {
		return totalWithOptional;
	}

	public void setTotalWithOptional(float totalWithOptional) {
		this.totalWithOptional = totalWithOptional;
	}
   
}
