package com.faherrera2.atlas_bank.transaction.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Validacion de concistencia
@Target(ElementType.TYPE) // Anotacion a nivel de clase con el TYPE
@Retention(RetentionPolicy.RUNTIME) // Hasta cuando vive la anotacion, mientras la app se este ejecutando
@Constraint(validatedBy = DifferentAccountValidator.class) // Ejecuta logica de validacion
public @interface DifferentAccounts { // Custom annotation se aplica a nivel de clase, decora una clase

    String message() default "La cuenta de origen y destino no pueden ser las mismas";
    Class<?>[] groups() default {}; // Validar ciertos campos al crear/actualizar
    Class<? extends Payload>[] payload() default {}; // clasificar la severidad del error, marcar validacion como advertencia
}
