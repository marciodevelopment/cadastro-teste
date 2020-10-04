package br.com.avaliacao.cadastro.repository;

import java.io.Serializable;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.com.avaliacao.cadastro.common.jpa.QueryBuilder;
import br.com.avaliacao.cadastro.entity.BaseEntity;

@Named
public abstract class AbstractEntityRepository<E extends BaseEntity, PK extends Serializable> { 
	@Inject
	private EntityManager entityManager;
	
	public Optional<E> findBy(Number primaryKey) {
		return Optional.of(entityManager.find(getEntityClass(), primaryKey));
	}
	
	@Transactional
	public E save(E entity) {
		if (!entity.isNew()) {
			return entityManager.merge(entity);
		}
		entityManager.persist(entity);
		return entity;
	}
	
	public void remove(E entity) {
		entityManager.remove(entity);
	}
	
	public Long count(QueryBuilder queryBuilder) {
		return (Long) queryBuilder
				.buildQuery(entityManager)
				.getSingleResult();
	}
	
	protected abstract Class<E> getEntityClass();

}
