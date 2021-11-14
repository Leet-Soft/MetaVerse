package uni.fmi.masters.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.TransactionalException;

import uni.fmi.masters.entities.UserEntity;

public class JPAUserRepository {
	
	public boolean registerUser(UserEntity user) {
		
		EntityManager em = getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(user);
			//добавям останали операции....
			
			em.getTransaction().commit();			
			
		}catch(TransactionalException e) {
			em.getTransaction().rollback();
			
			return false;			
		}finally {
			em.close();
		}
		
		return true;		
	}
	
	public UserEntity loginUser(String username, String password) {
		
		EntityManager em = getEntityManager();
		
		String query = "SELECT u FROM UserEntity u WHERE u.username = :user AND u.password = :pass";
		
		TypedQuery<UserEntity> tq = em.createQuery(query, UserEntity.class);
		tq.setParameter("user", username);
		tq.setParameter("pass", password);
		
		List<UserEntity> result = tq.getResultList();
		
		em.close();
		
		return result.size() == 1 ? result.get(0) : null;
		
//		if(result.size() == 1) {
//			return result.get(0);
//		}else {
//			return null;
//		}
		
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("MetaVerse");
		
		return factory.createEntityManager();
		
	}
}
