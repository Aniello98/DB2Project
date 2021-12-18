package it.polimi.db2.entities;

import javax.persistence.*;

import java.io.Serializable;


/**
 * The persistent class for the usertable database table.
 * 
 */
@Entity
@Table(name = "User", schema = "db2project")
@NamedQuery(name = "User.checkCredentials", query = "SELECT r FROM User r  WHERE r.username = ?1 and r.password = ?2")
@NamedQuery(name = "User.getByUsername", query = "SELECT r FROM User r  WHERE r.username = ?1")
@NamedQuery(name = "User.getByEmail", query = "SELECT r FROM User r  WHERE r.email = ?1")
@NamedQuery(name = "User.findInsolvent", query = "SELECT r FROM User r  WHERE r.insolvent = 1")

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	private String email;
	
	private boolean isEmployee;
	
	private boolean insolvent;
	

	public User() {
	}
	
	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public boolean isInsolvent() {
		return insolvent;
	}

	public void setInsolvent(boolean insolvent) {
		this.insolvent = insolvent;
	}


	
	/*
	public List<Order> getOrders(){
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;	}
		
	*/
	
	

}