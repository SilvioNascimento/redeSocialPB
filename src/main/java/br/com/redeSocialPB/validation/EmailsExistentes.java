package br.com.redeSocialPB.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailsExistentesValidator.class)
public @interface EmailsExistentes {
    String message() default "Email inválido. Por favor, fornece um " +
            "email que termine com @gmail.com, @outlook.com, @yahoo.com, " +
            "@protomail.com, @zohomail.com, @aol.com, @icloud.com, " +
            "@mail.com, @gmx.com ou @yandex.com";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
