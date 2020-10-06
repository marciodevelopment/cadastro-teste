package br.com.avaliacao.cadastro.web.util;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.MatchMode;

import br.com.avaliacao.cadastro.common.jpa.type.MatchModeQueryType;

public class MatchModelUtil {
	private static Map<MatchMode, MatchModeQueryType> mathModePrimeParaSql = new HashMap<>(2);
	private MatchModelUtil() {}
	
	public static MatchModeQueryType getMatchModeQueryType(MatchMode matchMode) {
		mathModePrimeParaSql.put(MatchMode.EQUALS, MatchModeQueryType.EQUALS);
		mathModePrimeParaSql.put(MatchMode.STARTS_WITH, MatchModeQueryType.STARTS_WITH);

		return mathModePrimeParaSql.get(matchMode);
	}
}
