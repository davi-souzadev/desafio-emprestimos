package org.desafio.emprestimo.dto;

import org.desafio.emprestimo.entity.Loan;

import java.util.List;

public class LoanResponseDTO {
    public String customer;

    public List<Loan> loans;
}
