package br.com.avaliacao.cadastro.entity;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;

import br.com.avaliacao.cadastro.common.exception.ApplicationRunTimeException;

public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 584422402102788202L;

	public boolean isNew() {
		try {
			Field field = FieldUtils.
					getFieldsListWithAnnotation(this.getClass(), javax.persistence.Id.class).get(0);
			return FieldUtils.readField(field, this, true) == null;
		} catch (IllegalAccessException e) {
			throw new ApplicationRunTimeException(e);
		}
	}
}
