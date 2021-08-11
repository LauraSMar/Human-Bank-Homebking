package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Consumption;
import com.mindhub.homebanking.models.Type;

import java.time.LocalDateTime;

public class ConsumptionDTO {

    private long id;
    private Type consumptionsType;
    private Integer tradeNumber;
    private double amount;
    private String description;
    private LocalDateTime date;
    private Integer payments;


    public ConsumptionDTO( ) {}

    public ConsumptionDTO(Consumption consumption){

        this.id=consumption.getId();
        this.consumptionsType=consumption.getConsumptionsType();
        this.tradeNumber=consumption.getTradeNumber();
        this.amount=consumption.getAmount();
        this.description= consumption.getDescription();
        this.date=consumption.getDate();
        this.payments=consumption.getPayments();

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
}
