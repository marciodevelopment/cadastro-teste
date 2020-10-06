package br.com.avaliacao.cadastro.common.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import br.com.avaliacao.cadastro.common.constraint.annotation.Telefone;
import br.com.avaliacao.cadastro.common.util.TelefoneUtil;

public class NumeroTelefoneConstraint implements ConstraintValidator<Telefone, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value)) {
			return true;
		}
		return TelefoneUtil.numeroTelefoneValido(value);
	}
}
