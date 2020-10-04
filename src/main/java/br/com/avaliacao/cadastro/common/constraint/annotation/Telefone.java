package br.com.avaliacao.cadastro.common.constraint.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.avaliacao.cadastro.common.constraint.NumeroTelefoneContraint;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumeroTelefoneContraint.class)
public @interface Telefone {
   String message() default "Número de telefone inválido";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}