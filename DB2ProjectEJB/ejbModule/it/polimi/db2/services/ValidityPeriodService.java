package it.polimi.db2.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import it.polimi.db2.entities.ValidityPeriod;
import it.polimi.db2.exceptions.*;


@Stateless
public class ValidityPeriodService {
	@PersistenceContext(unitName = "ProjectService")
	private EntityManager em;

	public ValidityPeriodService() {
	}
	
	
	public ValidityPeriod findPeriodById(int id) throws ProjectException {
		ValidityPeriod validityPeriod = null;
		
		try {
			validityPeriod = em.find(ValidityPeriod.class, id);
		}catch(PersistenceException e){
			throw new ProjectException("Cannot load service packages");
		}
		
		return validityPeriod;
	}
	
	public List<ValidityPeriod> findByParameters(int months, float fee) {
		return em.createNamedQuery("ValidityPeriod.findByParameters", ValidityPeriod.class).setParameter(1, months).setParameter(2, fee).getResultList();
	}
	
	public void createValidityPeriod(ValidityPeriod v) {
		em.persist(v);
	}

	
	
}
