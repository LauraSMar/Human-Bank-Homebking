package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.TypeAccount;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;



public class AccountDTO {

    private long id;
    private String number;
    private LocalDateTime creationDate;
    private double Balance;
    private TypeAccount typeAccount;
    private Set<TransactionDTO> transactions = new HashSet<>();
    private ClientDTO clientDTO;





    public AccountDTO(Account account){
        this.id= account.getId();
        this.number= account.getNumber();
        this.creationDate=account.getCreationDate();
        this.Balance= account.getBalance();
        this.typeAccount=account.getTypeAccount();
        this.transactions=account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());






    }





    public String getNumber() {
        return number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionDTO> transactions) {
        this.transactions = transactions;
    }





    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

}
