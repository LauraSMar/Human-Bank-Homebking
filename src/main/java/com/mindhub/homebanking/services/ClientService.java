package com.mindhub.homebanking.services;


import com.mindhub.homebanking.models.Client;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface ClientService {
// Estos metodos sin su cuerpo solo declaracion//


    boolean saveClient(Client client);
    List<Client> getClients();
    Optional<Client> getClient(Long IdClient);

}
