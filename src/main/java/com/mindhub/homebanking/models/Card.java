package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
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

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @OneToMany(mappedBy="card", fetch=FetchType.EAGER)
    private Set<Consumption> consumptions = new HashSet<>();




    public Card() { }




    public Card(String cardHolder, TypeCard typecard, ColorCard colorCard, String numbercard, LocalDate fromDate, LocalDate untilDate, String cvv, boolean deleted, double available, Client client){

        this.cardHolder=cardHolder;
        this.typeCard=typecard;
        this.colorCard=colorCard;
        this.numbercard=numbercard;
        this.fromDate=fromDate;
        this.untilDate=untilDate;
        this.cvv=cvv;
        this.deleted=deleted;
        this.available=available;
        this.client=client;


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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public Set<Consumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(Set<Consumption> consumptions) {
        this.consumptions = consumptions;
    }
}
