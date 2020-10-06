package br.com.avaliacao.cadastro.libs.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;

import br.com.avaliacao.cadastro.libs.teste.util.EntityParaTeste;

class CommonsLangTest {

	@Test
	void recuperarValorPorAnotacao() throws IllegalAccessException {
		int expected = 10;
		EntityParaTeste entity = new EntityParaTeste();
		entity.setId(expected);
		Field field = FieldUtils.
		getFieldsListWithAnnotation(entity.getClass(), javax.persistence.Id.class).get(0);
		Object actual = FieldUtils.readField(field, entity, true);
		assertEquals(expected, actual);
	}
}
