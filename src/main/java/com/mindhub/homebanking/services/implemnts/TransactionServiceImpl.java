package com.mindhub.homebanking.services.implemnts;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountService accountService;




    @Override
    public List<Transaction> findByCreatedBetweenDates(Client client, String string, LocalDateTime date, LocalDateTime date2) {

        Account account=accountService.findbyNumber(string);
        List<Transaction> list = new ArrayList<>(); for (Transaction e : account.getTransactions()) {
            if (e.getDate().isAfter(date) && e.getDate().isBefore(date2)) {
                list.add(e);
            }
        }


        return list;
    }
}

