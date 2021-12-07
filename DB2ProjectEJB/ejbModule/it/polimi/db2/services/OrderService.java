package it.polimi.db2.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import it.polimi.db2.entities.Order;
import it.polimi.db2.entities.User;
import it.polimi.db2.exceptions.UpdateProfileException;

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
		System.out.println("Eseguo findOrder("+id+")");
		return em.find(Order.class, id);
	}
	
	public void updateOrder(Order order) {
		try {
			System.out.println("Eseguo merge");
			em.merge(order);
		} catch (PersistenceException e) {
			System.out.println("Errore durante la merge");
			throw e;
		}
	}
	
	public List<Order> findRejectedByUser(User user){
		List<Order> rejectedOrders = new ArrayList<Order>();
		
		rejectedOrders = em.createNamedQuery("Order.findRejected", Order.class).setParameter(1, user)
						.getResultList();
		
		return rejectedOrders;
	}
}
