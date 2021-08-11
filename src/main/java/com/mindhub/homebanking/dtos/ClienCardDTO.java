package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Card;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienCardDTO {

    private long id;
    private String cardNumberCard;
    private String cardHolder;
    private Set<ConsumptionDTO> consumptions = new HashSet<>();
    private ClientDTO clientDTO;


    public ClienCardDTO(Card card, ConsumptionDTO consumptionDTO, ClientDTO clientDTO){

        this.id= card.getId();;
        this.cardNumberCard= card.getNumbercard();
        this.cardHolder= card.getCardHolder();
        this.consumptions=card.getConsumptions().stream().map(ConsumptionDTO::new).collect(Collectors.toSet());

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumberCard() {
        return cardNumberCard;
    }

    public void setCardNumberCard(String cardNumberCard) {
        this.cardNumberCard = cardNumberCard;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Set<ConsumptionDTO> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(Set<ConsumptionDTO> consumptions) {
        this.consumptions = consumptions;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }
}

