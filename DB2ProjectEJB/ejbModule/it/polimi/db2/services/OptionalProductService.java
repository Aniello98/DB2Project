package it.polimi.db2.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import it.polimi.db2.entities.OptionalProduct;
import it.polimi.db2.exceptions.*;


@Stateless
public class OptionalProductService {
	@PersistenceContext(unitName = "ProjectService")
	private EntityManager em;

	public OptionalProductService() {
	}
	
	
	public OptionalProduct findProductById(int id) throws ProjectException {
		OptionalProduct optionalProduct = null;
		
		try {
			optionalProduct = em.find(OptionalProduct.class, id);
		}catch(PersistenceException e){
			throw new ProjectException("Cannot load service packages");
		}
		
		return optionalProduct;
	}

	
	
}
