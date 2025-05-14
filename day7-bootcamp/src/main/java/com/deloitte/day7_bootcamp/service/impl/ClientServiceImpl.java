package com.deloitte.day7_bootcamp.service.impl;


import com.deloitte.day7_bootcamp.domain.model.Client;
import com.deloitte.day7_bootcamp.domain.repository.ClientRepository;
import com.deloitte.day7_bootcamp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Client not found with id "+ id));
    }

    @Override
    public Client create(Client client) {
        if (client.getId() != null && clientRepository.existsById(client.getId())) {
            throw new IllegalArgumentException("Client with id " + client.getId() + " already exists");
        }
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email " + client.getEmail() + " is already in use");
        }

        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        Client existingClient = findById(id);
        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());
        return clientRepository.save(existingClient);
    }

    @Override
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new NoSuchElementException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }
}
