package org.desafio.emprestimo.dto;

import org.desafio.emprestimo.entity.Loan;

import java.util.List;

public class LoanResponseDTO {
    public String customer;

    public List<Loan> loans;

    public LoanResponseDTO(String customer, List<Loan> loans) {
        this.customer = customer;
        this.loans = loans;
    }
}
