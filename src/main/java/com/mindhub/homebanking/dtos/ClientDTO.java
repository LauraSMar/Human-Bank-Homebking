package com.mindhub.homebanking.dtos;


import com.mindhub.homebanking.models.Client;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<AccountDTO> accounts=new HashSet<>();

    private Set<ClientLoanDTO>clientLoan = new HashSet<>();
    private Set<CardDTO> cards = new HashSet<>();

    public ClientDTO(){}

    public ClientDTO(Client client){

    this.id=client.getId();
    this.firstName=client.getFirstName();
    this.lastName= client.getLastName();
    this.email=client.getEmail();
    this.accounts=client.getAccount().stream().map(AccountDTO::new).collect(Collectors.toSet());
    this.clientLoan=client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
    this.cards=client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());



    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDTO> accounts) {
        this.accounts = accounts;
    }



    public Set<ClientLoanDTO> getClientLoan() {
        return clientLoan;
    }


    public void setClientLoan(Set<ClientLoanDTO> clientLoan) {
        this.clientLoan = clientLoan;
    }


    public Set<CardDTO> getCards() {
        return cards;
    }

    public void setCards(Set<CardDTO> cards) {
        this.cards = cards;
    }
}
