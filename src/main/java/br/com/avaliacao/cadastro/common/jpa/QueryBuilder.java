package br.com.avaliacao.cadastro.common.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class QueryBuilder {
	private StringBuilder hql = new StringBuilder();
	private Map<String, Object> parameters = new HashMap<>(1);
	private String namedQuery;
	private Integer firstResult;
	private Integer maxResult;
	private Integer pageNumber;
	private Integer pageSize;

	private boolean isNamedQuery() {
		return namedQuery != null;
	}

	public QueryBuilder addNamedQuery(String namedQuery) {
		this.namedQuery = namedQuery;
		return this;
	}

	public QueryBuilder addParamenter(String name, Object value) {
		parameters.put(name, value);
		return this;
	}

	public QueryBuilder appendHql(String hql) {
		this.hql.append(hql).append("\n");
		return this;
	}

	public Query buildQuery(EntityManager entityManager) {
		calcPagination();
		final Query query = createQuery(entityManager);
		addFirstResult(query);
		addMaxResult(query);
		this
		.parameters.forEach((name, value) -> query.setParameter(name, value));
		return query;		
	}

	private void addMaxResult(Query query) {
		if (this.maxResult != null)
			query.setMaxResults(maxResult);
	}

	private void addFirstResult(Query query) {
		if (this.firstResult != null)
			query.setFirstResult(this.firstResult);
	}

	private Query createQuery(EntityManager entityManager) {
		if (isNamedQuery()) {
			return entityManager.createNamedQuery(this.namedQuery);
		}
		return entityManager.createQuery(this.hql.toString());
	}
	
	private void calcPagination() {
		if (pageNumber == null || pageSize == null)
			return;
		firstResult = (pageNumber - 1) * pageSize;
		maxResult = pageSize;
	}

	public QueryBuilder setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
		return this;
	}

	public QueryBuilder setMaxResults(Integer maxResults) {
		this.maxResult = maxResults;
		return this;
	}

	public QueryBuilder setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber + 1;
		return this;
	}

	public QueryBuilder setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

}
