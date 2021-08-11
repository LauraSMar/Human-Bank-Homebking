package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ColorCard;

import java.util.Optional;

public interface CardService {


    void createNewCard(Client client);
    String generatorNumberCard();
    void createNewCreditCard(Client client, ColorCard colorCard);
    Optional<Card> findCardsbyId(Long idCard);
    void deleteById(Long idCard);

}
