package br.com.avaliacao.cadastro.common.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TelefoneUtilTest {

	@Test
	void deveRetirarFormatacaoTelefoneComTracos() {
		String actual = TelefoneUtil.retirarFormatacao("41-3213-4554");
		String expected = "4132134554";
		assertEquals(expected, actual);
	}
	
	@Test
	void deveRetirarFormatacaoTelefoneComParenteses() {
		String actual = TelefoneUtil.retirarFormatacao("(41)-3213-4554");
		String expected = "4132134554";
		assertEquals(expected, actual);
	}

	@Test
	void deveRetornarTrueParaNumeroDeTelefoneFixoValido() {
		boolean actual = TelefoneUtil.numeroTelefoneValido("(41)3213-4564");
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	void deveRetornarTrueParaNumeroDeCelularValido() {
		boolean actual = TelefoneUtil.numeroTelefoneValido("(41)93213-4564");
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	void deveRetornarFalseParaNumeroDeTelefoneFixoInvalido() {
		boolean actual = TelefoneUtil.numeroTelefoneValido("(41)323-4564");
		boolean expected = false;
		assertEquals(expected, actual);
		
	}
	
	@Test
	void deveRetornarFalseParaNumeroDeCelularInvalido() {
		boolean actual = TelefoneUtil.numeroTelefoneValido("(41)993213-4564");
		boolean expected = false;
		assertEquals(expected, actual);
		
	}
	
	@Test
	void deveRetornarFalseParaNumeroDeTelefoneNulo() {
		boolean actual = TelefoneUtil.numeroTelefoneValido(null);
		boolean expected = false;
		assertEquals(expected, actual);
		
	}
	
	@Test
	void deveRetornarFalseParaNumeroDeTelefoneComLetras() {
		boolean actual = TelefoneUtil.numeroTelefoneValido("41-45A5-4887");
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void deveFormatarCelular() {
		String actual = TelefoneUtil.formatar("41996357686");
		String expected = "(41) 996357686";
		assertEquals(expected, actual);
	}
	
	@Test
	void deveFormatarFixo() {
		String actual = TelefoneUtil.formatar("4199635768");
		String expected = "(41) 99635768";
		assertEquals(expected, actual);
	}
	
	

}
