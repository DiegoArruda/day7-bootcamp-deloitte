package com.deloitte.day7_bootcamp.domain.repository;

import com.deloitte.day7_bootcamp.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByEmail(String email);
}
