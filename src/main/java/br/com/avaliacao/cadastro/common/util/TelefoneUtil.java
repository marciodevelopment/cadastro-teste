package br.com.avaliacao.cadastro.common.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

import br.com.avaliacao.cadastro.common.exception.ApplicationRunTimeException;

public class TelefoneUtil {

	private static final int QTDE_CARACTERES_TELEFONE_FIXO = 10;
	private static final int QTDE_CARACTERES_TELEFONE_CELULAR = 11;
	private static final String MASCARA_FIXO = "(##) ########";
	private static final String MASCARA_CELULAR = "(##) ##########";

	private TelefoneUtil() {}
	
	public static String retirarFormatacao(String nrTelefone) {
		if (StringUtils.isBlank(nrTelefone)) {
			return nrTelefone;
		}
		return nrTelefone.replaceAll("[-.()]", "").replace(" ", "").trim();
	}
	
	public static boolean numeroTelefoneValido(String nrTelefone) {
		if (StringUtils.isEmpty(nrTelefone))
			return false;
		String nrTelefoneSemFormatacao = retirarFormatacao(nrTelefone);
		return (nrTelefoneSemFormatacao.trim().length() == QTDE_CARACTERES_TELEFONE_FIXO ||
				nrTelefoneSemFormatacao.trim().length() == QTDE_CARACTERES_TELEFONE_CELULAR) &&
				StringUtils.isNumeric(nrTelefoneSemFormatacao);
	}

	public static String formatar(String nrTelefone) {
		try {
			if (!numeroTelefoneValido(nrTelefone))
				return nrTelefone;
			return insereMascara(retirarFormatacao(nrTelefone)).trim();
		} catch (ParseException e) {
			throw new ApplicationRunTimeException(e);
		}
	}
	
	private static String insereMascara(String nrTelefone) throws ParseException {
		MaskFormatter mask = new MaskFormatter();
		mask.setValueContainsLiteralCharacters(false);
		mask.setMask(MASCARA_FIXO);
		if (nrTelefone.trim().length() == QTDE_CARACTERES_TELEFONE_CELULAR)
			mask.setMask(MASCARA_CELULAR);	
		return mask.valueToString(nrTelefone); 
	}
}
