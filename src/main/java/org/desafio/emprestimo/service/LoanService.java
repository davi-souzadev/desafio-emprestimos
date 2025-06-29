package org.desafio.emprestimo.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.desafio.emprestimo.dto.LoanRequestDTO;
import org.desafio.emprestimo.dto.LoanResponseDTO;
import org.desafio.emprestimo.entity.Customer;
import org.desafio.emprestimo.entity.Loan;
import org.desafio.emprestimo.enums.LoanType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LoanService {

    private static final BigDecimal THREE_THOUSAND = BigDecimal.valueOf(3000);
    private static final BigDecimal FIVE_THOUSAND = BigDecimal.valueOf(5000);
    private static final double PERSONAL_RATE = 4D;
    private static final double GUARANTEED_RATE = 3D;
    private static final double CONSIGNMENT_RATE = 2D;

    public LoanResponseDTO verifyLoanProposals(LoanRequestDTO loanRequest) {
        List<Loan> loans = new ArrayList<>();


        if (isLessOrEqualThan(loanRequest, THREE_THOUSAND)) {
            loans.add(createLoan(LoanType.PERSONAL, PERSONAL_RATE));
            loans.add(createLoan(LoanType.GUARANTEED, GUARANTEED_RATE));
        }

        if (isIncomeBetween(loanRequest, THREE_THOUSAND, FIVE_THOUSAND) & isLocationAt(loanRequest, "SP") & isAgeUnder(loanRequest, 30)) {
            loans.add(createLoan(LoanType.PERSONAL, PERSONAL_RATE));
            loans.add(createLoan(LoanType.GUARANTEED, GUARANTEED_RATE));
        }

        if(isIncomeMoreThan(loanRequest, FIVE_THOUSAND)) {
            loans.add(createLoan(LoanType.PERSONAL, PERSONAL_RATE));
            loans.add(createLoan(LoanType.GUARANTEED, GUARANTEED_RATE));
            loans.add(createLoan(LoanType.CONSIGNMENT, CONSIGNMENT_RATE));
        }

        return new LoanResponseDTO(loanRequest.name, loans);
    }

    private Loan createLoan(LoanType type, double rate) {
        return new Loan(type, rate);
    }

    private boolean isLessOrEqualThan(LoanRequestDTO request, BigDecimal value) {
        return request.income.compareTo(value) <= 0;
    }

    private boolean isIncomeBetween(LoanRequestDTO request, BigDecimal min, BigDecimal max) {
        return request.income.compareTo(min) > 0
                && request.income.compareTo(max) < 0;
    }

    private boolean isIncomeMoreThan(LoanRequestDTO request, BigDecimal value) {
        return request.income.compareTo(value) > 0;
    }

    private boolean isLocationAt(LoanRequestDTO request, String location) {
        return request.location.equalsIgnoreCase(location);
    }

    private boolean isAgeUnder(LoanRequestDTO request, int age) {
        return request.age < age;
    }
}
