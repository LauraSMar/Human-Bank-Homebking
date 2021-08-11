package com.mindhub.homebanking.services.implemnts;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service // agregar anotacion de Servicio//
public class ClientServiceImpl implements ClientService {


    @Autowired
    ClientRepository clientRepository;



    @Override
    public boolean saveClient(Client client) {

        return clientRepository.save(client) != null;

    }

    @Override
    public List<Client> getClients() {
            List<Client> clientList=clientRepository.findAll();
            return clientList;


    }

    @Override
    public Optional<Client> getClient(Long IdClient){
        return clientRepository.findById(IdClient);
    }




}
