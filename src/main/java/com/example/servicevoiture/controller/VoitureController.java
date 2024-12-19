package com.example.servicevoiture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicevoiture.entities.Client;
import com.example.servicevoiture.entities.Voiture;
import com.example.servicevoiture.repositories.VoitureRepository;
import com.example.servicevoiture.service.ClientService;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private ClientService clientService;
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            Voiture voiture = voitureRepository.findById(id)
                    .orElseThrow(() -> new Exception("Voiture Inexistante"));

            System.out.println("Fetching client with id: " + voiture.getIdClient());

            Client client = clientService.getClientById(voiture.getIdClient());

            System.out.println("Client fetched: " + client);

            voiture.setClient(client);
            return ResponseEntity.ok(voiture);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération du client pour la voiture ID: " + id);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur : " + e.getMessage());
        }
    }




    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<Voiture> voitures = voitureRepository.findAll();

            for (Voiture voiture : voitures) {
                try {
                    Client client = clientService.getClientById(voiture.getIdClient());
                    voiture.setClient(client);
                } catch (Exception e) {
                    System.out.println("Erreur lors de la récupération du client pour la voiture ID: " + voiture.getId());
                    voiture.setClient(null); // Laisser le client null si une erreur se produit
                }
            }

            return ResponseEntity.ok(voitures);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching voitures: " + e.getMessage());
        }
    }


 
    @GetMapping("/client/{id}")
    public ResponseEntity<List<Voiture>> findByClient(@PathVariable Long id) {
        try {
            Client client = clientService.getClientById(id);
            if (client != null) {
                // Filter cars for this specific client by idClient field
                List<Voiture> voitures = voitureRepository.findByIdClient(id);
                return ResponseEntity.ok(voitures);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    
   
    @PostMapping("/voitures")
    public ResponseEntity<Voiture> createVoiture(@RequestBody Voiture voiture) {
        try {
            Voiture savedVoiture = voitureRepository.save(voiture);
            return ResponseEntity.ok(savedVoiture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
