package com.example.servicevoiture.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import lombok.Data;


import java.util.List;

@Data

public class Client {
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Voiture> voitures;
    
    private int age;


    
    
    public Client() {}
    
    public Client(Long id, String name, int age, List<Voiture> voitures) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.voitures = voitures;
	}

	public List<Voiture> getVoitures() {
		return voitures;
	}

	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
