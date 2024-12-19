package com.example.servicevoiture.entities;

import jakarta.persistence.*;

@Entity

public class Voiture {
    @Id
    @GeneratedValue
    private Long id;
    
    private String marque;
    
    private String matricule;
    
    
    
    
    private String model;
    
    
    
    
    @Column(name = "id_client")
    private Long idClient;
    
    @Transient
    private Client client;

	public Voiture(Long id, String marque, String matricule, String model, Long idClient, Client client) {
		super();
		this.id = id;
		this.marque = marque;
		this.matricule = matricule;
		this.model = model;
		this.idClient = idClient;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	} 
    public Voiture() {}
}

