package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientService clientService;

    @RequestMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientService.getClients().stream().map(ClientDTO::new).collect(toList());
    }

    @RequestMapping("clients/{id}")

    public List<ClientDTO> getClient(@PathVariable Long id) {


        return clientService.getClient(id).stream().map(ClientDTO::new).collect(Collectors.toList());
    }


    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (clientRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }
        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @RequestMapping("/clients/current")
    public ClientDTO getAll(Authentication authentication) {
       Client client =clientRepository.findByEmail(authentication.getName());
        ClientDTO dto=new ClientDTO(client);
        return dto;
    }


  /*  @RequestMapping("clients/current/cards")
    public ClienCardDTO clienCardDTO(Authentication authentication){
        Client client=clientRepository.findByEmail(authentication.getName());

        ClienCardDTO dto= new ClienCardDTO(client.getCards(),c);
    }*/
   /* public Map<String, Object> toDTO() {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", getId());
        dto.put("name", getName());
        return dto;
    }*/



}
