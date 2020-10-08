package br.com.avaliacao.cadastro.common.constraint.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.avaliacao.cadastro.common.constraint.NumeroTelefoneConstraint;

@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumeroTelefoneConstraint.class)
public @interface Telefone {
   String message() default "{telefone.telefone}";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}