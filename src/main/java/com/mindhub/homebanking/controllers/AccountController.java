package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.TypeAccount;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class AccountController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {

        return accountService.getAccounts().stream().map(AccountDTO::new).collect(toList());

    }

    @PostMapping("/clients/current/search")
    public ResponseEntity<?> searchAccount(Authentication authentication,@RequestParam String accNumber){

      Account account= accountService.findbyNumber(accNumber);
            if(account.getNumber().isEmpty()){
              return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
            }
      String nameClient= account.getClient().getFirstName() +" "+ account.getClient().getLastName();

        System.out.println("The name is " + nameClient);

      return new ResponseEntity<>(nameClient,HttpStatus.OK);
    }

    @GetMapping("/accounts/{id}")

    public List<AccountDTO> getClient(@PathVariable Long id) {

        return accountService.getAccount(id).stream().map(AccountDTO::new).collect(Collectors.toList());

    }


    @PostMapping(path = "/clients/current/accounts")
    public ResponseEntity<?> createAccount(Authentication authentication, @RequestParam TypeAccount typeAccount) { // Traigo param de autenticacion porque esta logueado mi cliente//


        try {
            Client myclient = clientRepository.findByEmail(authentication.getName());
            accountService.createAccount(myclient,typeAccount);
            System.out.println("ok ");
            return new ResponseEntity<>("Sucess",HttpStatus.CREATED);

        } catch (Exception exception) {
            System.out.println("Three accounts in off");

            return new ResponseEntity<>("You have 3 accounts",HttpStatus.FORBIDDEN);


        }

    }
}