package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ColorCard;
import com.mindhub.homebanking.models.TypeCard;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.mindhub.homebanking.models.TypeCard.Credito;
import static com.mindhub.homebanking.models.TypeCard.Debito;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CardController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CardService cardService;


    @PostMapping(path = "/clients/current/cards")
    public void createNewCard(Authentication authentication, @RequestParam TypeCard typeCard, @RequestParam ColorCard colorCard) { // Traigo param de autenticacion porque esta logueado mi cliente//

        Client myclient = clientRepository.findByEmail(authentication.getName());
        System.out.println(typeCard);
        System.out.println(myclient.getCards().stream().filter(e -> e.getTypeCard() == Debito).count() + "Tarjetas Debito");
        System.out.println(myclient.getCards().stream().filter(e -> e.getTypeCard() == Credito).count() + "Tarjetas Credito");

       if(typeCard==Debito) {
           if (myclient.getCards().stream().filter(e -> e.getTypeCard() == Debito).count() < 3) {

               cardService.createNewCard(myclient);
               System.out.println("Debit Card Created");



           }
       }

       if(typeCard==Credito) {

           if (myclient.getCards().stream().filter(e -> e.getTypeCard() == Credito).count() < 3) {

               cardService.createNewCreditCard(myclient, colorCard);
               System.out.println("Credit Card Created");


           } else {
               System.out.println("Your Cards are in off");
           


           }

       }

    }
    @DeleteMapping(path  = "/clients/current/cards/{idCard}")
    public boolean deleteCard(Authentication authentication, @PathVariable long idCard){

        Client myclient = clientRepository.findByEmail(authentication.getName());

        Optional<Card> myCard =cardService.findCardsbyId(idCard);

        if(myCard.isPresent()){

            cardService.deleteById(idCard);
            System.out.println("Card deleted" + cardService.findCardsbyId(idCard));
            return true;
        }


        return false;



    };

}



