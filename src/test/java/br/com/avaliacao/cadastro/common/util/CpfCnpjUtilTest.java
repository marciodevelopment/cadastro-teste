package br.com.avaliacao.cadastro.common.util;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

class CpfCnpjUtilTest {

	@Test
	void deveRetirarFormatacaoDeCpf() {
		String expected = "95523707104"; 
		String actual = CpfCnpjUtil.retirarFormatacao("955.237.071-04");
		assertEquals(expected, actual);
	}
	
	@Test
	void deveRetirarFormatacaoDeCnpj() {
		String expected = "88029299000158"; 
		String actual = CpfCnpjUtil.retirarFormatacao("88.029.299/0001-58");
		assertEquals(expected, actual);
	}
	
	@Test
	void naoDeveDarErroAoEnviarNullParaRetirarFormatacao() {
		String expected = null; 
		String actual = CpfCnpjUtil.retirarFormatacao(null);
		assertEquals(expected, actual);
	}

	@Test
	void deveConverterCpf() throws ParseException {
		String cpf = "95523707104";
		String actual = CpfCnpjUtil.formatar(cpf);
		String expected = "955.237.071-04";
		assertEquals(expected, actual);
	}
	
	@Test
	void deveConverterCnpj() throws ParseException {
		String cnpj = "00000000000101";
		String actual = CpfCnpjUtil.formatar(cnpj);
		String expected = "00.000.000/0001-01";
		assertEquals(expected, actual);
	}

}
