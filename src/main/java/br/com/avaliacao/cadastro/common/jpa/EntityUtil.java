package br.com.avaliacao.cadastro.common.jpa;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;

import br.com.avaliacao.cadastro.common.exception.ApplicationRunTimeException;
import br.com.avaliacao.cadastro.entity.BaseEntity;

public class EntityUtil {
	private EntityUtil() {}
	
	public static boolean isNew(BaseEntity entity) {
		return getId(entity) != null;
	}
	
	public static Integer getId(BaseEntity entity) {
		try {
			Field field = FieldUtils.
					getFieldsListWithAnnotation(entity.getClass(), javax.persistence.Id.class).get(0);
			return (Integer) FieldUtils.readField(field, entity, true);
		} catch (IllegalAccessException e) {
			throw new ApplicationRunTimeException(e);
		}
	}
}
