package uni.fmi.masters.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.TransactionalException;

public abstract class BaseRepository<T> {

	private Class<T> typeParameter;
	
	public BaseRepository(Class<T> typeParameter) {
		this.typeParameter = typeParameter;		
	}
	
	public boolean addOrUpdate(T entity) {
		EntityManager em = getEntityManager();
		
		try {
			em.getTransaction().begin();			
			em.persist(entity);			
			em.getTransaction().commit();			
			
		}catch(TransactionalException e) {
			em.getTransaction().rollback();
			return false;			
		}finally {
			em.close();
		}
		
		return true;
		
	}
	
	public boolean delete(T entity) {
		EntityManager em = getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.flush();
			em.getTransaction().commit();
			
		}catch(TransactionalException e) {
			em.getTransaction().rollback();
			return false;
		}finally {
			em.close();
		}
		
		return true;
	}
	
	public boolean deleteById(int id) {
		
		EntityManager em = getEntityManager();
		
		try {
			em.getTransaction().begin();
			
			T entity = em.find(typeParameter, id);
			
			if(entity != null) {
				em.remove(entity);
				em.flush();				
			}
			
			em.getTransaction().commit();
			
		}catch(TransactionalException e) {
			em.getTransaction().rollback();
			return false;
		}finally {
			em.close();
		}
		
		return true;		
	}
	
	public List<T> getListBy(String column, String value){
		EntityManager em = getEntityManager();
		
		//"SELECT u FROM UserEntity u WHERE u.username = :user AND u.password = :pass";
		String query = "SELECT t FROM " + 
						typeParameter.getSimpleName() + " t " +
						" WHERE " + column + " = '" + value + "'"; 
		
		TypedQuery<T> tq = em.createQuery(query, typeParameter);
		
		return tq.getResultList();		
	}	
	
	protected EntityManager getEntityManager() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("MetaVerse");
		
		return factory.createEntityManager();
		
	}
	
}
