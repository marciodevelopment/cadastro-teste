package br.com.avaliacao.cadastro.common.util;

import org.apache.commons.lang3.StringUtils;

public class TelefoneUtil {

	private static final int QTDE_CARACTERES_TELEFONE_FIXO = 10;
	private static final int QTDE_CARACTERES_TELEFONE_CELULAR = 11;

	private TelefoneUtil() {}
	
	public static String retirarFormatacao(String nrTelefone) {
		if (StringUtils.isEmpty(nrTelefone)) {
			return nrTelefone;
		}
		return nrTelefone.replaceAll("[-.()]", "").trim();
	}
	
	public static boolean numeroTelefoneValido(String nrTelefone) {
		if (StringUtils.isEmpty(nrTelefone))
			return false;
		String nrTelefoneSemFormatacao = retirarFormatacao(nrTelefone);
		return (nrTelefoneSemFormatacao.trim().length() == QTDE_CARACTERES_TELEFONE_FIXO ||
				nrTelefoneSemFormatacao.trim().length() == QTDE_CARACTERES_TELEFONE_CELULAR) &&
				StringUtils.isNumeric(nrTelefoneSemFormatacao);
	}
}
