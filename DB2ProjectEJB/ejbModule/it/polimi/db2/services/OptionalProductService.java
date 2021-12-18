package it.polimi.db2.services;

import java.util.ArrayList;
import java.util.List;

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
			throw new ProjectException("Cannot load all optional products");
		}
		
		return optionalProduct;
	}
	
	public List<OptionalProduct> findByName(String name) throws ProjectException{
		List<OptionalProduct> optionalProducts=new ArrayList<>();
		
		try {
			optionalProducts=em.createNamedQuery("OptionalProduct.findByName",OptionalProduct.class).setParameter(1,name).getResultList();
		}catch(PersistenceException e){
			throw new ProjectException("Cannot load optional products by name");
		}
		
		return optionalProducts;
	}
	
	public void createOptionalProduct(OptionalProduct op) {
		em.persist(op);
	}
	
	public List<OptionalProduct> findAllProducts() {
		return em.createNamedQuery("OptionalProduct.findAll", OptionalProduct.class).getResultList();
	}

	
	
}
