package org.desafio.emprestimo.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.desafio.emprestimo.dto.LoanRequestDTO;
import org.desafio.emprestimo.service.LoanService;

@Path("customer-loans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanResource {

    @Inject
    LoanService loanService;

    @POST
    public Response create(@NotNull @Valid LoanRequestDTO loanRequest) {
        return Response.ok(loanService.verifyLoanProposals(loanRequest)).build();
    }

}
