package com.mindhub.homebanking.services.implemnts;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TypeAccount;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public boolean saveAccount(Account account) {
        return false;
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> account= accountRepository.findAll();
        return account;
    }

    @Override
    public Optional<Account> getAccount(Long IdAccount) {
        return accountRepository.findById(IdAccount);
    }

    @Override
    public String getAccountNumber() {

        String numberAccount="VIN0"+getRandomNumber(1000,1500);

        return numberAccount;
    }

    @Override
    public void createAccount(Client client, TypeAccount typeAccount) {
            Account myaccount=new Account(getAccountNumber(), LocalDateTime.now(),0, typeAccount,client);
            accountRepository.save(myaccount);


    }



    @Override
    public Account findbyNumber(String number) {
        return accountRepository.findByNumber(number);
    }



    public Integer getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private class Transactions {
    }
}
