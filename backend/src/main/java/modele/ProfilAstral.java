/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author alexandre armbruster, harold martin
 */
@Entity
public class ProfilAstral {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String signeZodiaque;
    private String signeChinois;
    private String couleur;
    private String animal;

    public ProfilAstral() {
    }
    
    public ProfilAstral(String signeZodiaque, String signeChinois, String couleur, String animal) {
        this.signeZodiaque = signeZodiaque;
        this.signeChinois = signeChinois;
        this.couleur = couleur;
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }
    
    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "ProfilAstral{" + "id=" + id + ", signeZodiaque=" + signeZodiaque + ", signeChinois=" + signeChinois + ", couleur=" + couleur + ", animal=" + animal + '}';
    }
    
}
