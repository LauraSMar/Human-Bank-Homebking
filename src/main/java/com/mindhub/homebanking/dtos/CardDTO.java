package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.ColorCard;
import com.mindhub.homebanking.models.TypeCard;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CardDTO {
    private long id;
    private String cardHolder;
    private TypeCard typeCard;
    private ColorCard colorCard;
    private String numbercard;
    private LocalDate fromDate;
    private LocalDate untilDate;
    private String cvv;
    private boolean deleted;
    private double available;
    private Set<ConsumptionDTO> consumptions=new HashSet<>();

    public CardDTO(){}

    public CardDTO(Card card){
        this.id=card.getId();
        this.cardHolder=card.getCardHolder();
        this.typeCard=card.getTypeCard();
        this.colorCard=card.getColorCard();
        this.numbercard=card.getNumbercard();
        this.fromDate=card.getFromDate();
        this.untilDate=card.getUntilDate();
        this.cvv=card.getCvv();
        this.deleted=card.isDeleted();
        this.available=card.getAvailable();
        this.consumptions=card.getConsumptions().stream().map(ConsumptionDTO::new).collect(Collectors.toSet());



    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public TypeCard getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(TypeCard typeCard) {
        this.typeCard = typeCard;
    }

    public ColorCard getColorCard() {
        return colorCard;
    }

    public void setColorCard(ColorCard colorCard) {
        this.colorCard = colorCard;
    }

    public String getNumbercard() {
        return numbercard;
    }

    public void setNumbercard(String numbercard) {
        this.numbercard = numbercard;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(LocalDate untilDate) {
        this.untilDate = untilDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    public Set<ConsumptionDTO> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(Set<ConsumptionDTO> consumptions) {
        this.consumptions = consumptions;
    }
}
