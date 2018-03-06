package com.oapc.model;

import javax.persistence.*;

@Entity
@Table(name = "productes")
public class Producte {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private String color;
    
    private Long diametre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

	public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public Long getDiametre() {
        return diametre;
    }

    public void setDiametre(Long diametre) {
        this.diametre = diametre;
    }

}