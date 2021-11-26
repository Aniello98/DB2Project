package it.polimi.db2.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.entities.Order;

@Stateless
public class OrderService {
	@PersistenceContext(unitName = "ProjectService")
	private EntityManager em;
	
	public OrderService() {
		
	}
	
	public void persistOrder(Order order) {
		em.persist(order);
	}
	
	public Order findOrder(int id) {
		return em.find(Order.class, id);
	}
	
	public void updateOrder(Order order) {
		em.merge(order);
	}
}
