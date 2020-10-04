package br.com.avaliacao.cadastro.repository;



import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {

	@PersistenceContext(unitName = "cadastro")
    private EntityManager entityManager;
 
    @Produces
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
  
    public void closeEntityManager(@Disposes EntityManager manager) {
        if (manager.isOpen()) {
            manager.close();
        }
    }
}
