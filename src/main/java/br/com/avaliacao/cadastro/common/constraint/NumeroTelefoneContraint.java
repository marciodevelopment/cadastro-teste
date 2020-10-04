package br.com.avaliacao.cadastro.common.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import br.com.avaliacao.cadastro.common.constraint.annotation.Telefone;
import br.com.avaliacao.cadastro.common.util.TelefoneUtil;

public class NumeroTelefoneContraint implements ConstraintValidator<Telefone, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		if (StringUtils.isBlank(value)) {
			return false;
		}
		return TelefoneUtil.numeroTelefoneValido(value);
	}
}
