package it.polimi.db2.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import it.polimi.db2.entities.Order;
import it.polimi.db2.entities.Service;
import it.polimi.db2.entities.User;
import it.polimi.db2.exceptions.UpdateProfileException;

@Stateless
public class ServiceService {
	@PersistenceContext(unitName = "ProjectService")
	private EntityManager em;
	
	public ServiceService() {
		
	}
	
	
	public List<Service> findAllServices() {
		return em.createNamedQuery("Service.findAll", Service.class).getResultList();
	}
	
	public Service findById(int id) {
		return em.find(Service.class, id);
	}
	
	
}
