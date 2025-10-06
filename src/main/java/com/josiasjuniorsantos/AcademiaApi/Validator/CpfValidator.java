package com.josiasjuniorsantos.AcademiaApi.Validator;

import com.josiasjuniorsantos.AcademiaApi.Anotation.Cpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.isEmpty()) return false;
        cpf = cpf.replaceAll("\\D", ""); // remove pontos e traços
        if (cpf.length() != 11) return false;

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int sum1 = 0, sum2 = 0;
            for (int i = 0; i < 9; i++) {
                int num = Integer.parseInt(cpf.substring(i, i + 1));
                sum1 += num * (10 - i);
                sum2 += num * (11 - i);
            }

            int check1 = sum1 % 11 < 2 ? 0 : 11 - sum1 % 11;
            sum2 += check1 * 2;
            int check2 = sum2 % 11 < 2 ? 0 : 11 - sum2 % 11;

            return check1 == Integer.parseInt(cpf.substring(9, 10))
                    && check2 == Integer.parseInt(cpf.substring(10, 11));

        } catch (NumberFormatException e) {
            return false;
        }
    }
}