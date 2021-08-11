package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {

		SpringApplication.run(HomebankingApplication.class, args);


	}


	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository,CardRepository cardRepository,ConsumptionRepository consumptionRepository) {
		return (args) -> {

			LocalDateTime today = LocalDateTime.now();
			LocalDateTime tomorrow = today.plusDays(1);



			Client client1 = clientRepository.save(new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("2501")));
			Client client2 = clientRepository.save(new Client("Laura", "Segura", "seguramartinezlaura2@gmail.com", passwordEncoder.encode("1805")));
			Client client3= clientRepository.save(new Client("Pablo", "Rodriguez", "pablorodriguez@gmail.com", passwordEncoder.encode("3008")));

			Account account1= accountRepository.save(new Account("VIN00125", today, 5000,TypeAccount.CA, client1));
			Account account4= accountRepository.save(new Account("VIN00501",today,1500,TypeAccount.CC,client1));
			Account account5= accountRepository.save(new Account("VIN15222",today,100,TypeAccount.CA,client1));
			Account account2= accountRepository.save(new Account( "VIN15002", tomorrow, 7500,TypeAccount.CC,client3));
			Account account3= accountRepository.save(new Account("VIN30003",today,12500.50,TypeAccount.CA,client2));

			Type valueTYPE=Type.CRE;

			Transaction transaction1 = transactionRepository.save(new Transaction(Type.CRE,2000,"PAGO",today,account2));
			Transaction transaction2 = transactionRepository.save(new Transaction(Type.DEB,-1500,"TRANSFERENCIA",today,account2));
			Transaction transaction3 = transactionRepository.save(new Transaction(Type.DEB, -3500,"CHEQUE",today,account1));
			Transaction transaction4 = transactionRepository.save(new Transaction(Type.CRE,1500,"DEPOSITO",today,account1));
			Transaction transaction5 = transactionRepository.save(new Transaction(Type.BAN,-500,"COMISION BANCARIA",today,account1));
			Transaction transaction6 = transactionRepository.save(new Transaction(Type.DEB,-1500,"PAGOTC",today,account1));
			Transaction transaction7 = transactionRepository.save(new Transaction(Type.CRE,1500,"PAGO",today,account2));
			Transaction transaction8 = transactionRepository.save(new Transaction(Type.DEB,-2000,"PAGO ALQUILER",today,account2));

			List<Integer> payments1 = List.of(12,24,36,48,60);
			List<Integer> payments2 = List.of(6,12,24);
			List<Integer> payments3 = List.of(6,12,24,36);

			List<Integer> interest1 = List.of(18,24,36,56,72);
			List<Integer> interest2 = List.of(12,24,36);
			List<Integer> interest3 = List.of(12,24,36,48);


			Loan loan1=loanRepository.save(new Loan("Morgage Loan", 500000,payments1,interest1));
			Loan loan2=loanRepository.save(new Loan("Personal Loan",100000,payments2,interest2));
			Loan loan3=loanRepository.save(new Loan("Car Loan",300000,payments3,interest3));

			ClientLoan clientLoan1=clientLoanRepository.save(new ClientLoan(400000,60,72,client1,loan1));
			ClientLoan clientLoan2=clientLoanRepository.save(new ClientLoan(50000,12,24,client1,loan2));
			ClientLoan clientLoan3=clientLoanRepository.save(new ClientLoan(100000,24,36,client2,loan2));
			ClientLoan clientLoan4=clientLoanRepository.save(new ClientLoan(200000,36,48,client2,loan3));

			Card card1=cardRepository.save(new Card("Melba Morel",TypeCard.Credito,ColorCard.Gold,"4568250145612300", LocalDate.now(),LocalDate.now().plusDays(1825),"521", false,50000,client1));

			Card card2=cardRepository.save(new Card ("Melba Morel",TypeCard.Credito, ColorCard.Silver, "4568665325011400",LocalDate.now(),LocalDate.now().plusDays(1825),"625", false,80000,client1));

			Card card3=cardRepository.save(new Card("Laura Segura Martinez", TypeCard.Credito,ColorCard.Black,"5325456689741000", LocalDate.now(),LocalDate.now().plusDays(1825),"133",false,150000,client2));

			Card card4=cardRepository.save(new Card("Laura Segura Martinez", TypeCard.Credito,ColorCard.Silver,"4563123456789541",LocalDate.now(),LocalDate.now().plusDays(1825),"200",false,80000,client2));

            Consumption consumption1=consumptionRepository.save(new Consumption(Type.DEB,45678,12500,"Tkts aereos",LocalDateTime.now(),6,card1));
            Consumption consumption2=consumptionRepository.save(new Consumption(Type.DEB,45687,6500,"Supermercado",LocalDateTime.now(),3,card1));
            Consumption consumption3=consumptionRepository.save(new Consumption(Type.DEB,45699,2500,"Epec Luz",LocalDateTime.now(),1,card1));







		};



	}}
