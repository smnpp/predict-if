/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alexandre armbruster, harold martin
 */
@Entity
public class Consultation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String commentaire;
    private Boolean validation;
    @ManyToOne
    private Medium medium;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employe employe;

    public Consultation() {
    }

    public Consultation(Date date, Medium medium, Client client, Employe employe) {
        this.date = date;
        this.commentaire = "";
        this.validation = false;
        this.medium = medium;
        this.client = client;
        this.employe = employe;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Boolean getValidation() {
        return validation;
    }

    public Medium getMedium() {
        return medium;
    }

    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", date=" + date + ", commentaire=" + commentaire + ", validation=" + validation + '}';
    }
    
    
    
}
