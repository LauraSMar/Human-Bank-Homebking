package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private  String name;
    private double maxAmount;


    @ElementCollection
    @Column(name="payments")
    private List<Integer> payments = new ArrayList<>();
    @ElementCollection
    @Column(name="interest")
    private List<Integer> interest= new ArrayList<>();

    @OneToMany(mappedBy="loan", fetch=FetchType.EAGER)
    private Set<ClientLoan> clientsHashSet = new HashSet<>();

    // Set the relation between one loan to many movements of quotes



    public Loan(){}

    public Loan(String name, double maxAmount, List<Integer> payments,List<Integer> interest){
        this.name=name;
        this.maxAmount=maxAmount;
        this.payments= payments;
        this.interest=interest;

    }

    public  long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public List<Integer> getInterest() {
        return interest;
    }

    public void setInterest(List<Integer> interest) {
        this.interest = interest;
    }

    public Set<ClientLoan> getClientsHashSet() {
        return clientsHashSet;
    }

    public void setClientsHashSet(Set<ClientLoan> clientsHashSet) {
        this.clientsHashSet = clientsHashSet;
    }

    public Set<Client> getClient(){
        return clientsHashSet.stream().map(clientLoan -> clientLoan.getClient()).collect(Collectors.toSet());
    }

    public int findInterest(List<Integer> payments, int paymentreq) {

        int value = 0;
        if (payments.contains(paymentreq)) {
            int indice = payments.indexOf(paymentreq);
            value=interest.get(indice);

        }
        return value;
    }

}
