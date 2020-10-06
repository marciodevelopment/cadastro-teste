package br.com.avaliacao.cadastro.common.constraint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumeroTelefoneConstraintTest {

	NumeroTelefoneConstraint validador = new NumeroTelefoneConstraint();


	@Test
	void deveRetornarTrueParaTelefoneNulo() {
		boolean actual = validador.isValid(null, null);
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	void deveRetornarTrueParaTelefoneVazio() {
		boolean actual = validador.isValid("", null);
		boolean expected = true;
		assertEquals(expected, actual);
	}
}
