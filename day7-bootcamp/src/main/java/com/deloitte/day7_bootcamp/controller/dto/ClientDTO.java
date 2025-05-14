package com.deloitte.day7_bootcamp.controller.dto;

import com.deloitte.day7_bootcamp.domain.model.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDTO {
    private String name;
    private String email;

    public Client toEntity(){
        Client client = new Client();
        client.setName(this.getName());
        client.setEmail(this.getEmail());
        return client;
    }

    public static ClientDTO fromEntity(Client client){
        ClientDTO dto = new ClientDTO();
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        return dto;
    }
}