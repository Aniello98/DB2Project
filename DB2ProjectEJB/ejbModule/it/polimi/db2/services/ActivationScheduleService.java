package it.polimi.db2.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.views.ActivationSchedule;

@Stateless
public class ActivationScheduleService {
	@PersistenceContext(unitName = "ProjectService")
	private EntityManager em;

	
	public List<ActivationSchedule> getActivationSchedule(String username){
		return em.createNamedQuery("ActivationSchedule.findByUsername", ActivationSchedule.class).setParameter(1, username).getResultList();
	}
}
