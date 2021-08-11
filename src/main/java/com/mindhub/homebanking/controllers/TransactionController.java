package com.mindhub.homebanking.controllers;

import com.lowagie.text.DocumentException;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.parse;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TransactionController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardService cardService;


    @Transactional
    @PostMapping("/clients/current/transactions")
   public ResponseEntity<?> creatingTransfer(Authentication authentication, @RequestParam String accOrigin, @RequestParam String accDestiny, @RequestParam String description,@RequestParam double amount) {



        Client myclient = clientRepository.findByEmail(authentication.getName());

        Account myaccount = accountService.findbyNumber(accOrigin);
        Account otheraccount = accountService.findbyNumber( accDestiny);



        if (accDestiny.isEmpty()|| accOrigin.isEmpty()){
            return new ResponseEntity<>("Imposible to do the transaction", HttpStatus.FORBIDDEN);
        }
        if (myaccount == null || otheraccount==null || myaccount==otheraccount) {
            return new ResponseEntity<>("The origin or destiny account doesn't exist", HttpStatus.FORBIDDEN);
        }

       if( myaccount.getBalance()< amount ){

            return new ResponseEntity<>("Don't have the balance to do this operation", HttpStatus.FORBIDDEN);
      }




            Transaction debit = new Transaction(Type.DEB,-amount,description,LocalDateTime.now(),myaccount);
            transactionRepository.save(debit);
            myaccount.setBalance(myaccount.getBalance()-amount);
           // accountService.saveAccount(myaccount);
            System.out.println(myaccount.getBalance());

            Transaction credit = new Transaction(Type.CRE, amount,description, LocalDateTime.now(), otheraccount);
            transactionRepository.save(credit);
            otheraccount.setBalance(otheraccount.getBalance()+amount);
           // accountService.saveAccount(otheraccount);
            System.out.println(otheraccount.getBalance());
            System.out.println("Transference realized");

            return new ResponseEntity<>(HttpStatus.CREATED);




    }

    @Transactional
    @PostMapping("/clients/current/payments")
    public ResponseEntity<?> payingCard(Authentication authentication, @RequestParam String accOrigin, @RequestParam long idCard,@RequestParam double amount) {


        Client myclient = clientRepository.findByEmail(authentication.getName());

        Account myaccount = accountService.findbyNumber( accOrigin);
        Optional<Card> cardtoPay = cardService.findCardsbyId(idCard);


        if (accOrigin.isEmpty()) {
            return new ResponseEntity<>("Imposible to do the transaction", HttpStatus.FORBIDDEN);
        }
        if (!cardtoPay.isPresent()) {
            return new ResponseEntity<>("The card  doesn't exist", HttpStatus.FORBIDDEN);
        }

        if (amount <= myaccount.getBalance()) {

            String numberCard=cardtoPay.get().getNumbercard().substring(0,12).concat("xxxx");
            String description = "Payment Card " + numberCard;
            System.out.println(numberCard);


            Transaction debit = new Transaction(Type.DEB, -amount, description, LocalDateTime.now(), myaccount);
            transactionRepository.save(debit);
            myaccount.setBalance(myaccount.getBalance()-amount);
         //   accountService.saveAccount(myaccount);
            System.out.println(description);
            System.out.println("Payment realized");

            return new ResponseEntity<>(HttpStatus.CREATED);


       }


      return null;
    }



    @PostMapping ("/clients/current/export/pdf")
    public void ExportingToPDF(HttpServletResponse response, Authentication authentication,@RequestParam String accNumber,@RequestParam String dateIni, @RequestParam String dateEnd) throws DocumentException, IOException {

        Client client = clientRepository.findByEmail(authentication.getName());


        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Transactions" + accNumber + ".pdf";
        response.setHeader(headerKey, headerValue);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeIni = parse(dateIni, formatter);
        LocalDateTime dateTimeEnd= parse(dateEnd,formatter);

        System.out.println(dateTimeEnd + "and" + dateTimeIni);

        List<Transaction> listTransactions =transactionService.findByCreatedBetweenDates(client,accNumber,dateTimeIni,dateTimeEnd) ;
        System.out.println(response);
        TransactionPDFExporter exporter = new TransactionPDFExporter(listTransactions);
        exporter.usePDFExport(response);
        System.out.println("PDF created");

    }

}