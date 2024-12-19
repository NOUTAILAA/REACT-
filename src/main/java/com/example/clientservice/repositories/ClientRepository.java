package com.example.clientservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clientservice.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNameContainingIgnoreCase(String name);

}
