package br.com.avaliacao.cadastro.common.jpa;

import br.com.avaliacao.cadastro.common.jpa.type.MatchModeQueryType;

public class QueryFilter {
	private String field;
	private MatchModeQueryType matchMode;
	private Object value;
	private boolean isCaseInsensitive;
	
	public QueryFilter(String field, Object value, MatchModeQueryType matchMode, boolean isCaseInsensitive) {
		super();
		this.field = field;
		this.matchMode = matchMode;
		this.value = value;
		this.isCaseInsensitive = isCaseInsensitive;
	}

	public String getClauseWhere(String aliasEntity) {
		StringBuilder clause = new StringBuilder();
		clause
		.append(" where ")
		.append(getField(aliasEntity))
		.append(this.matchMode.getMatchValue())
		.append(getParameterNameJpql());
		return clause.toString();
	}
	
	private String getField(String aliasEntity) {
		StringBuilder hql = new StringBuilder();
		if (isCaseInsensitive) {
			return hql.append(aliasEntity).append(".").append(this.field).toString();
		}
		return hql.append("lower(").append(aliasEntity).append(".").append(this.field).append(")").toString();
	}
	
	public String getParameterNameJpql() {
		if (isCaseInsensitive) {
			return ":" + field;
		}
		return "lower(:" + field + ")";
	}
	
	public String getParameterName() {
		return field;
	}

	public Object getValue() {
		if (this.matchMode == MatchModeQueryType.STARTS_WITH) {
			return  value + "%";
		}
		return value;
	}
}
