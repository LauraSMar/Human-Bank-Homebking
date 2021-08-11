package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoanController{

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientLoanRepository clientLoanRepository;
    @Autowired
    private LoanRepository loanRepository;



    @GetMapping("/loans")
    public List<LoanDTO> getLoans() {

        return loanRepository.findAll().stream().map(LoanDTO::new).collect(toList());

    }

    @Transactional
    @PostMapping("/clients/current/myloans")
    public ResponseEntity<?> aplyingLoan(Authentication authentication,@RequestParam String accDestiny, @RequestParam Long IdLoan, @RequestParam Integer reqQuotes,@RequestParam double amount) {


        Client myclient = clientRepository.findByEmail(authentication.getName());
        Account myaccount = accountService.findbyNumber( accDestiny);

        Optional<Loan> mynewLoan = loanRepository.findById(IdLoan);


        if (!mynewLoan.isPresent()) {
            return new ResponseEntity<>("Doesn't exist this loan", HttpStatus.FORBIDDEN);
        }


        if (!mynewLoan.get().getPayments().contains(reqQuotes)) {

            return new ResponseEntity<>("Doesn't exist this quantity of quotes", HttpStatus.FORBIDDEN);
        }

        if (accDestiny.isEmpty() || amount > mynewLoan.get().getMaxAmount() || reqQuotes == 0) {
            return new ResponseEntity<>("Imposible to do the transaction", HttpStatus.FORBIDDEN);
        }

        if (myaccount == null) {
            return new ResponseEntity<>("Doesn't exist the account to send the aproved loan", HttpStatus.FORBIDDEN);
        }
        System.out.println("Loan Approved");

        int interest=mynewLoan.get().findInterest(mynewLoan.get().getPayments(), reqQuotes);


        ClientLoan clientLoan1 = clientLoanRepository.save(new ClientLoan(amount, reqQuotes, interest, myclient, mynewLoan.get()));

        Transaction credit = new Transaction(Type.CRE, amount, "LOAN APPROVED", LocalDateTime.now(), myaccount);
        transactionRepository.save(credit);
        myaccount.setBalance(myaccount.getBalance()+amount);
       // accountService.saveAccount(myaccount);

        System.out.println("Credited Loan");

        return new ResponseEntity<>(HttpStatus.CREATED);


    }
}