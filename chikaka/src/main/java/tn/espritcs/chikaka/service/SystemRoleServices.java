package tn.espritcs.chikaka.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.espritcs.chikaka.model.authentification.SystemRole;

@Stateless
public class SystemRoleServices {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public SystemRole getRoleByName(String roleName) throws Exception {
		Query query = em.createQuery("SELECT r FROM SystemRole r WHERE r.name = :roleName");
		query.setParameter("roleName", roleName);
		try{
			SystemRole result = (SystemRole) query.getSingleResult();
			return result;
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw e;
		}
	}
}
