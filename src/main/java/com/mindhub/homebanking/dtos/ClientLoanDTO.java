package com.mindhub.homebanking.dtos;


import com.mindhub.homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private long id;
    private long loan_id;
    private String name;
    private double requestAmount;
    private Integer quotes;
    private Integer interest;

    public ClientLoanDTO(){};

    public ClientLoanDTO( ClientLoan clientLoan){

        this.id= clientLoan.getId();
        this.loan_id=clientLoan.getLoan().getId();
        this.name=clientLoan.getLoan().getName();
        this.requestAmount=clientLoan.getAmount();
        this.quotes=clientLoan.getPayments();
        this.interest= clientLoan.getInterest();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(long loan_id) {
        this.loan_id = loan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(double requestAmount) {
        this.requestAmount = requestAmount;
    }

    public Integer getQuotes() {
        return quotes;
    }

    public void setQuotes(Integer quotes) {
        this.quotes = quotes;
    }

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }
}
