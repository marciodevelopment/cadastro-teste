package br.com.avaliacao.cadastro.common.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

public class CpfCnpjUtil {
	private static final int QTDE_CARACTERES_CPF = 11;
	private static final int  QTDE_CARACTERES_CNPJ = 14;
	private static final String MASCARA_CNPJ = "##.###.###/####-##";
	private static final String MASCARA_CPF = "###.###.###-##";
	private CpfCnpjUtil(){}
	
	public static String retirarFormatacao(String cpfCnpj) {
		if (StringUtils.isEmpty(cpfCnpj)) {
			return cpfCnpj;
		}
		return cpfCnpj.replaceAll("[-./]", "").trim();
	}
	
	public static String formatar(String cpfCnpj) {
		try {
			String cpfCnpjSemFormatacao = retirarFormatacao(cpfCnpj);
			if (valorCpfCnpjNaoTemTamanhoValido(cpfCnpjSemFormatacao)) {
				return cpfCnpj;
			}
			
			MaskFormatter mask = new MaskFormatter();
			mask.setValueContainsLiteralCharacters(false);
			if (utilizarMascaraParaCnpj(cpfCnpj)) {
				return formatarCnpj(cpfCnpjSemFormatacao, mask);
			}
			return formatarCpf(cpfCnpjSemFormatacao, mask);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private static String formatarCnpj(String cpfCnpj, MaskFormatter mask) throws ParseException {
		mask.setMask(MASCARA_CNPJ);
		return mask.valueToString(cpfCnpj); 
	}
	
	private static String formatarCpf(String cpfCnpj, MaskFormatter mask) throws ParseException {
		mask.setMask(MASCARA_CPF);
		return mask.valueToString(cpfCnpj); 
	}
	
	private static boolean utilizarMascaraParaCpf(String cpfCnpj) {
		return cpfCnpj.length() == QTDE_CARACTERES_CPF;
	}

	private static boolean utilizarMascaraParaCnpj(String cpfCnpj) {
		return cpfCnpj.length() == QTDE_CARACTERES_CNPJ;
	}

	private static boolean valorCpfCnpjNaoTemTamanhoValido(String cpfCnpj) {
		return StringUtils.isEmpty(cpfCnpj) ||
				(!utilizarMascaraParaCpf(cpfCnpj) && !utilizarMascaraParaCnpj(cpfCnpj));
	}
}
