package br.com.avaliacao.cadastro.common.constraint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class NotEmptyConstraintTest {

	@Test
	void deveRetornarFalseParaValorNull() {
		NotEmptyConstraint notEmpty = new NotEmptyConstraint();
		String valor = null;
		assertFalse(notEmpty.isValid(valor, null));
	}
	
	@Test
	void deveRetornarFalseParaStringVazia() {
		NotEmptyConstraint notEmpty = new NotEmptyConstraint();
		String valor = "  ";
		assertFalse(notEmpty.isValid(valor, null));
	}
	
	@Test
	void deveRetornarTrueParaStringNaoVazia() {
		NotEmptyConstraint notEmpty = new NotEmptyConstraint();
		String valor = "oi";
		assertTrue(notEmpty.isValid(valor, null));
	}
	
	@Test
	void deveRetornarTrueParaCollectionComElementos() {
		NotEmptyConstraint notEmpty = new NotEmptyConstraint();
		Set<String> set = new HashSet<>(Arrays.asList("valor"));
		assertTrue(notEmpty.isValid(set, null));
	}
	
	@Test
	void deveRetornarFalseParaCollectionVazia() {
		NotEmptyConstraint notEmpty = new NotEmptyConstraint();
		Set<String> set = new HashSet<>();
		assertFalse(notEmpty.isValid(set, null));
	}

}
