package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Loan;

import java.util.Collections;
import java.util.List;

public class LoanDTO {

    private long id;
    private  String name;
    private double maxAmount;
    private List<Integer> payments;
    private List<Integer> interest;


    public LoanDTO(Loan loan){
        this.id= loan.getId();
        this.name=loan.getName();
        this.maxAmount= loan.getMaxAmount();
        this.payments= loan.getPayments();
        this.interest=loan.getInterest();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public List<Integer> getInterest() {
        return interest;
    }

    public void setInterest(List<Integer> interest) {
        this.interest = interest;
    }




}
