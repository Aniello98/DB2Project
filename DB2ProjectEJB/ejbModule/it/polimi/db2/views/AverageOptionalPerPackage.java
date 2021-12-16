package it.polimi.db2.views;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AverageOptionalPerPackage
 *
 */@Entity
 @Table(name = "AverageOptionalPerPackage", schema = "db2project")
 @NamedQuery(name = "AverageOptionalPerPackage.findAll", query = "SELECT pp FROM AverageOptionalPerPackage pp")
public class AverageOptionalPerPackage implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public AverageOptionalPerPackage() {
		super();
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getTotalProductsSold() {
		return totalProductsSold;
	}

	public void setTotalProductsSold(int totalProductsSold) {
		this.totalProductsSold = totalProductsSold;
	}

	public float getAverageOptional() {
		return averageOptional;
	}

	public void setAverageOptional(float averageOptional) {
		this.averageOptional = averageOptional;
	}

	@Id
	private int packageId;
	
	private String packageName;
	
	private int totalProductsSold;
	
	private float averageOptional;
   
}
