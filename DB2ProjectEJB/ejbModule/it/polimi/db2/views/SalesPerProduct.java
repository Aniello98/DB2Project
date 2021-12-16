package it.polimi.db2.views;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SalesPerProduct
 *
 */
@Entity
@Table(name = "SalesPerProduct", schema = "db2project")
@NamedQuery(name = "SalesPerProduct.findAll", query = "SELECT pp FROM SalesPerProduct pp ORDER BY pp.salesValue")
public class SalesPerProduct implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int productId;
	
	private String productName;
	
	private float salesValue;
	

	public SalesPerProduct() {
		super();
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public float getSalesValue() {
		return salesValue;
	}


	public void setSalesValue(float salesValue) {
		this.salesValue = salesValue;
	}
	
	
   
}
