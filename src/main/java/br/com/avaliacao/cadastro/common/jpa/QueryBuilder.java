package br.com.avaliacao.cadastro.common.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class QueryBuilder {
	private StringBuilder hql = new StringBuilder();
	private Map<String, Object> parameters = new HashMap<String, Object>(1);
	private String namedQuery;
	
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
		Query query = createQuery(entityManager);
		this.parameters.forEach((name, value) -> query.setParameter(name, value));
		return query;		
	}
	
	private Query createQuery(EntityManager entityManager) {
		if (isNamedQuery()) {
			return entityManager.createNamedQuery(this.namedQuery);
		}
		return entityManager.createQuery(this.hql.toString());
	}
	
}
