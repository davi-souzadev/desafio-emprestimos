package org.desafio.emprestimo.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.desafio.emprestimo.dto.LoanRequestDTO;
import org.desafio.emprestimo.dto.LoanResponseDTO;
import org.desafio.emprestimo.entity.Loan;
import org.desafio.emprestimo.enums.LoanType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LoanService {

    private LoanResponseDTO createLoanResponseObject(LoanRequestDTO loanRequest, LoanResponseDTO loanResponse) {
        List<Loan> loanList = new ArrayList<>();
        loanResponse.customer = loanRequest.name;
        loanResponse.loans = loanList;
        return loanResponse;
    }

    private Loan setLoanProperties(LoanType loanType, Double interestRate) {
        Loan loan = new Loan();
        loan.setType(loanType);
        loan.setInterest_rate(interestRate);
        return loan;
    }

    public LoanResponseDTO verifyLoanProposals(LoanRequestDTO loanRequest) {
        BigDecimal threeThousand = BigDecimal.valueOf(3000);
        BigDecimal fiveThousand = BigDecimal.valueOf(5000);
        LoanResponseDTO loanResponse = new LoanResponseDTO();
        LoanResponseDTO loanObjectResponse = createLoanResponseObject(loanRequest, loanResponse);
        boolean isLessOrEqualThanThreeThousand = loanRequest.income.compareTo(threeThousand) <= 0;
        boolean isMoreThanThreeKAndLessThanFiveK =
                loanRequest.income.compareTo(threeThousand) > 0
                & loanRequest.income.compareTo(fiveThousand) < 0;
        boolean livesInSP = loanRequest.location.equals("SP");
        boolean isMoreThanFive5K = loanRequest.income.compareTo(fiveThousand) > 0;
        boolean isUnder30YearsOld = loanRequest.age < 30;

        if (isLessOrEqualThanThreeThousand) {
            loanObjectResponse.loans.add(setLoanProperties(LoanType.PERSONAL, 4D));
            loanObjectResponse.loans.add(setLoanProperties(LoanType.GUARANTEED, 3D));
        }

        if (isMoreThanThreeKAndLessThanFiveK & livesInSP & isUnder30YearsOld) {
            loanObjectResponse.loans.add(setLoanProperties(LoanType.PERSONAL, 4D));
            loanObjectResponse.loans.add(setLoanProperties(LoanType.GUARANTEED, 3D));
        }

        if(isMoreThanFive5K) {
            loanObjectResponse.loans.add(setLoanProperties(LoanType.PERSONAL, 4D));
            loanObjectResponse.loans.add(setLoanProperties(LoanType.GUARANTEED, 3D));
            loanObjectResponse.loans.add(setLoanProperties(LoanType.CONSIGNMENT, 2D));
        }

        return loanResponse;
    }
}
