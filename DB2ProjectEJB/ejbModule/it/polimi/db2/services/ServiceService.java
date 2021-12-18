package it.polimi.db2.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.entities.Service;

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
