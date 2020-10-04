package br.com.avaliacao.cadastro.common.constraint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumeroTelefoneContraintTest {

	NumeroTelefoneContraint validador = new NumeroTelefoneContraint();


	@Test
	void deveRetornarTrueParaTelefoneNulo() {
		boolean actual = validador.isValid(null, null);
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	void deveRetornarFalseParaTelefoneVazio() {
		boolean actual = validador.isValid("", null);
		boolean expected = false;
		assertEquals(expected, actual);
	}
}
