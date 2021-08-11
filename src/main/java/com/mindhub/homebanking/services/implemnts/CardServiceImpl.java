package com.mindhub.homebanking.services.implemnts;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public  class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Override
    public String generatorNumberCard() {

        String numberCard = "4560" + getRandomNumber(1000, 2000) + getRandomNumber(1000, 1200) + "1255";

        System.out.println(numberCard);
        return numberCard;

    }
    @Override
    public void createNewCard(Client client) {
        String cardHolder = client.getFirstName() + " " + client.getLastName();
        Card mycard = new Card(cardHolder, TypeCard.Debito, ColorCard.Blue, generatorNumberCard(), LocalDate.now(), LocalDate.now().plusDays(360), "000",false,0, client);
        cardRepository.save(mycard);
    }
    @Override
    public void createNewCreditCard(Client client, ColorCard colorCard) {

        String cardHolder = client.getFirstName() + " " + client.getLastName();

        if (colorCard == ColorCard.Silver) {
           Card   mycard = new Card(cardHolder, TypeCard.Credito, colorCard, generatorNumberCard(), LocalDate.now(), LocalDate.now().plusDays(360), "156", false, 80000, client);
           cardRepository.save(mycard);
        }
        if (colorCard == ColorCard.Gold) {
            Card   mycard = new Card(cardHolder, TypeCard.Credito, colorCard, generatorNumberCard(), LocalDate.now(), LocalDate.now().plusDays(360), "156", false, 50000, client);
            cardRepository.save(mycard);
        }
        if (colorCard == ColorCard.Black) {
            Card   mycard = new Card(cardHolder, TypeCard.Credito, colorCard, generatorNumberCard(), LocalDate.now(), LocalDate.now().plusDays(360), "156", false, 150000, client);
            cardRepository.save(mycard);
        }


    }

    @Override
    public Optional<Card> findCardsbyId(Long idCard) {
        return cardRepository.findById(idCard);
    }

    @Override
    public void deleteById(Long idCard) {
        Card card= cardRepository.getById(idCard);
       if( !card.isDeleted()){
           card.setDeleted(true);
           cardRepository.save(card);

       }
    }

    public Integer getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }




}

