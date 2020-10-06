package br.com.avaliacao.cadastro.common.jpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.avaliacao.cadastro.common.jpa.type.MatchModeQueryType;

class QueryFilterTest {

	@Test
	void testaMatchModeEquals() {
		QueryFilter query = new QueryFilter("pessoa", "carlos", MatchModeQueryType.EQUALS, false);
		String actual = query.getClauseWhere("p");
		String expected = " where lower(p.pessoa) = lower(:pessoa)";
		assertEquals(expected, actual);
	}
	
	@Test
	void testaMatchModeStartWith() {
		QueryFilter query = new QueryFilter("pessoa", "carlos", MatchModeQueryType.STARTS_WITH, false);
		String actual = query.getClauseWhere("p");
		String expected = " where lower(p.pessoa) like lower(:pessoa)";
		assertEquals(expected, actual);
	}

}
