package it.polimi.db2.services;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.NonUniqueResultException;
import it.polimi.db2.entities.User;
import it.polimi.db2.exceptions.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Stateless
public class UserService {
	@PersistenceContext(unitName = "ProjectService")
	private EntityManager em;

	public UserService() {
	}

	public User checkCredentials(String usrn, String pwd) throws CredentialsException, NonUniqueResultException {
		List<User> uList = null;
		try {
			uList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, usrn).setParameter(2, pwd)
					.getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify credentals");
		}
		if (uList.isEmpty())
			return null;
		else if (uList.size() == 1)
			return uList.get(0);
		throw new NonUniqueResultException("More than one user registered with same credentials");

	}
	
	public User findUserById(int id) {
		return em.find(User.class, id);
	}
	
	public List<User> findUserByUsername(String usrn) {
		List<User> user = null;
		user = em.createNamedQuery("User.getByUsername", User.class).setParameter(1, usrn).getResultList();
		
		System.out.print(user);
		
		return user;
	}
	
	public List<User> findUserByEmail(String mail) {
		List<User> user = null;
		user = em.createNamedQuery("User.getByEmail", User.class).setParameter(1, mail).getResultList();
		
		System.out.println("Cerco mail");
		
		for(User u: user){
			System.out.println(u.getEmail());
		}
		
		return user;
	}
	
	public void createUser(String username, String mail, String pwd) throws SignUpException {
		User user = new User();
		user.setEmail(mail);
		user.setUsername(username);
		user.setPassword(pwd);
		
		em.persist(user);
		em.flush();
		
	}
	 

	public void updateProfile(User u) throws UpdateProfileException {
		try {
			em.merge(u);
		} catch (PersistenceException e) {
			throw new UpdateProfileException("Could not change profile");
		}
	}
}
