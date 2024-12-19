package com.example.clientservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clientservice.entities.Client;
import com.example.clientservice.repositories.ClientRepository;
@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:3000") 
public class ClientController {
 @Autowired
    private ClientRepository clientRepository;
 
 
 
 
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        try {
            Client client = clientRepository.findById(id)
                    .orElseThrow(() -> new Exception("Client introuvable avec l'ID : " + id));
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        try {
            Client client = clientRepository.findById(id)
                    .orElseThrow(() -> new Exception("Client introuvable avec l'ID : " + id));

            client.setName(clientDetails.getName());
            client.setAge(clientDetails.getAge());

            Client updatedClient = clientRepository.save(client);
            return ResponseEntity.ok(updatedClient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/searchByName/{name}")
    public ResponseEntity<List<Client>> getClientByName(@PathVariable String name) {
        List<Client> clients = clientRepository.findByNameContainingIgnoreCase(name);
        if (clients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(clients);
    }


    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
