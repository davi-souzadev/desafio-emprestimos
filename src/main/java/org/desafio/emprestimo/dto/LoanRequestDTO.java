package org.desafio.emprestimo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class LoanRequestDTO {
    @NotNull
    @Min(value = 18, message = "Deve ser maior de idade!")
    public int age;

    @NotBlank
    public String cpf;

    @NotBlank
    public String name;

    @NotNull
    @PositiveOrZero
    public BigDecimal income;

    @NotBlank
    public String location;
}
