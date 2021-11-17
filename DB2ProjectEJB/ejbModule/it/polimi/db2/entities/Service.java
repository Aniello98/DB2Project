package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: Service
 *
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "Service", schema = "db2project")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int minutes;
	
	private int sms;
	
	private int giga;
	
	private float feeMinutes;
	
	private float feeSms;
	
	private float feeGiga;
	
	private String type;
	
	//@ManyToMany(mappedBy="services")
	//private Collection<ServicePackage> servicePackages;
	
	public Service() {
		super();
	}



	public int getMinutes() {
		return minutes;
	}



	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}



	public int getSms() {
		return sms;
	}



	public void setSms(int sms) {
		this.sms = sms;
	}



	public int getGiga() {
		return giga;
	}



	public void setGiga(int giga) {
		this.giga = giga;
	}



	public float getFeeMinutes() {
		return feeMinutes;
	}



	public void setFeeMinutes(float feeMinutes) {
		this.feeMinutes = feeMinutes;
	}



	public float getFeeSms() {
		return feeSms;
	}



	public void setFeeSms(float feeSms) {
		this.feeSms = feeSms;
	}



	public float getFeeGiga() {
		return feeGiga;
	}



	public void setFeeGiga(float feeGiga) {
		this.feeGiga = feeGiga;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}
   
}
