package ec.edu.ups.demo.helloworld.dao;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import ec.edu.ups.demo.helloworld.model.Persona;

@Stateless
public class PersonaDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void insert(Persona op) {
		em.persist(op);
	}
	
	@Transactional
	public List<Persona> getList(){
		List<Persona> listado = new ArrayList<Persona>();
		
		String jpql="SELECT op FROM Persona op";
		Query query = em.createQuery(jpql, Persona.class);
		listado = query.getResultList();
		
		return listado;
	}
}
