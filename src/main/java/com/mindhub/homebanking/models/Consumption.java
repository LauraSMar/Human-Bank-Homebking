package com.mindhub.homebanking.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Consumption {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Type consumptionsType;
    private Integer tradeNumber;
    private double amount;
    private String description;
    private LocalDateTime date;
    private Integer payments;

    // Set the relation between comsumptions with credit card

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="cardId")
    private Card card;

    public Consumption(){}

    public Consumption(Type consumptionsType, Integer tradeNumber, double amount, String description, LocalDateTime date, Integer payments, Card card){

        this.consumptionsType= consumptionsType;
        this.tradeNumber=tradeNumber;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.payments=payments;
        this.card=card;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getConsumptionsType() {
        return consumptionsType;
    }

    public void setConsumptionsType(Type consumptionsType) {
        this.consumptionsType = consumptionsType;
    }

    public Integer getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(Integer tradeNumber) {
        this.tradeNumber = tradeNumber;
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

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }
    @JsonIgnore
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}






