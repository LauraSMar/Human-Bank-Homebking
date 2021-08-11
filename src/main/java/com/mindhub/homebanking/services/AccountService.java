package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.TypeAccount;

import java.util.List;
import java.util.Optional;

public interface AccountService {


    // Estos metodos sin su cuerpo//


    boolean saveAccount (Account account);
    List<Account> getAccounts();
    Optional<Account> getAccount(Long IdAccount);

    String getAccountNumber();

    void createAccount(Client client, TypeAccount typeAccount);


    Account findbyNumber(String number);



}
