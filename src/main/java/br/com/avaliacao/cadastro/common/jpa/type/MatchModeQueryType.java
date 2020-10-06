package br.com.avaliacao.cadastro.common.jpa.type;

public enum MatchModeQueryType {
	EQUALS(" = "),
	STARTS_WITH(" like ");

	private String matchValue;
	MatchModeQueryType(String matchValue) {
		this.matchValue = matchValue;
	}
	public String getMatchValue() {
		return matchValue;
	}
	
}
