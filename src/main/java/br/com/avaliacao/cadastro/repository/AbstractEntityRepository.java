package br.com.avaliacao.cadastro.repository;

import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.avaliacao.cadastro.common.jpa.QueryBuilder;
import br.com.avaliacao.cadastro.entity.BaseEntity;

@Named
public abstract class AbstractEntityRepository<E extends BaseEntity> { 
	@PersistenceContext(unitName = "puCadastro")
	private EntityManager entityManager;
	
	protected abstract Class<E> getEntityClass();
	
	public Optional<E> findBy(Number primaryKey) {
		return Optional.ofNullable(entityManager.find(getEntityClass(), primaryKey));
	}
	
	@Transactional
	public E save(E entity) {
		if (!entity.isNew()) {
			return entityManager.merge(entity);
		}
		entityManager.persist(entity);
		return entity;
	}
	
	@Transactional
	public void delete(E entity) {
		StringBuilder hql = new StringBuilder();
		hql
		.append(" delete from ")
		.append(entity.getClass().getSimpleName()).append(" e ")
		.append(" where e = :entity");
		entityManager.createQuery(hql.toString()).setParameter("entity", entity).executeUpdate();
	}
	
	public Long count(QueryBuilder queryBuilder) {
		return (Long) queryBuilder
				.buildQuery(entityManager)
				.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public E getSingleResult(QueryBuilder queryBuilder) {
		return (E) queryBuilder
				.buildQuery(entityManager)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<E> search(QueryBuilder queryBuilder) {
		return queryBuilder
				.buildQuery(entityManager)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> searchDto(QueryBuilder queryBuilder) {
		return queryBuilder
				.buildQuery(entityManager)
				.getResultList();
	}


}
