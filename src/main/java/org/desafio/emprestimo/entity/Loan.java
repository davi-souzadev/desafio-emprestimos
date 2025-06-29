package org.desafio.emprestimo.entity;

import org.desafio.emprestimo.enums.LoanType;

public class Loan {

    private LoanType type;

    private Double interest_rate;

    public Loan(LoanType type, Double interest_rate) {
        this.type = type;
        this.interest_rate = interest_rate;
    }

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public Double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(Double interest_rate) {
        this.interest_rate = interest_rate;
    }
}
