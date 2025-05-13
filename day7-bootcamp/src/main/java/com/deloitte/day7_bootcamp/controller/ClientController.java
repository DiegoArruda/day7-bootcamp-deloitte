package com.deloitte.day7_bootcamp.controller;

import com.deloitte.day7_bootcamp.domain.model.Client;
import com.deloitte.day7_bootcamp.controller.dto.ClientDTO;
import com.deloitte.day7_bootcamp.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<Client> clients = clientService.findAll();
        List<ClientDTO> clientsDTOs = clients.stream()
                .map(ClientDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientsDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        Client client = clientService.findById(id);
        return ResponseEntity.ok(ClientDTO.fromEntity(client));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDTO) {
        Client newClient = clientDTO.toEntity(clientDTO);
        Client createdClient = clientService.create(newClient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdClient.getId())
                .toUri();
        return ResponseEntity.created(location).body(ClientDTO.fromEntity(createdClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        Client client = clientDTO.toEntity(clientDTO);
        Client updatedClient = clientService.update(id, client);
        return ResponseEntity.ok(ClientDTO.fromEntity(updatedClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
