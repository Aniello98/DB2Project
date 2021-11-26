package it.polimi.db2.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.NonUniqueResultException;

import it.polimi.db2.entities.ServicePackage;
import it.polimi.db2.entities.User;
import it.polimi.db2.exceptions.*;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ServicePackageService {
	@PersistenceContext(unitName = "ProjectService")
	private EntityManager em;

	public ServicePackageService() {
	}
	
	public List<ServicePackage> findAll() throws ProjectException{
		List<ServicePackage> servicePackages = null;
		
		try {
			servicePackages = em.createNamedQuery("ServicePackage.findAll", ServicePackage.class).getResultList();
		}catch(PersistenceException e){
			throw new ProjectException("Cannot load service packages");
		}
		return servicePackages;
	}
	
	public ServicePackage findPackageById(int id) throws ProjectException {
		ServicePackage servicePackage = null;
		
		try {
			servicePackage = em.find(ServicePackage.class, id);
		}catch(PersistenceException e){
			throw new ProjectException("Cannot load service packages");
		}
		
		return servicePackage;
	}

	/*public void updateServicePackage(ServicePackage p) throws UpdateProfileException {
		try {
			em.merge(p);
		} catch (PersistenceException e) {
			throw new UpdateProfileException("Could not change profile");
		}
	}*/
}
