package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.Type;

import java.time.LocalDateTime;

public class TransactionDTO {

    private long id;
    private Type transactionType;
    private double amount;
    private String description;
    private LocalDateTime date;

    public TransactionDTO(Type cre, double amount, String loan_approved, LocalDateTime now, AccountDTO myaccount) {}

    public TransactionDTO(Transaction transaction){

        this.id=transaction.getId();
        this.transactionType=transaction.getTransactionType();
        this.amount=transaction.getAmount();
        this.description= transaction.getDescription();
        this.date=transaction.getDate();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
    }

    public Type getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Type transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
