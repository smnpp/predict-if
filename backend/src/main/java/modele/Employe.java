/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alexandre armbruster, harold martin
 */
@Entity
public class Employe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private Boolean genre; // Homme : 0/False, Femme : 1/True
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    @Column(unique = true)
    private String tel;    
    private String adressePostale;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private Boolean disponibilite;
    private Integer nombreConsultations;
    @OneToMany(mappedBy="employe")
    private List<Consultation> consultations;

    public Employe() {
        this.consultations = new ArrayList<>();
    }

    public Employe(String nom, String prenom, Boolean genre, String mail, String motDePasse, String tel, String adressePostale, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.tel = tel;
        this.adressePostale = adressePostale;
        this.dateNaissance = dateNaissance;
        this.disponibilite = true;
        this.nombreConsultations = 0;
        this.consultations = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Boolean getGenre() {
        return genre;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getTel() {
        return tel;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public Integer getNombreConsultations() {
        return nombreConsultations;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGenre(Boolean genre) {
        this.genre = genre;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setNombreConsultations(Integer nombreConsultations) {
        this.nombreConsultations = nombreConsultations;
    }

    public void addConsultation(Consultation consultation){
        this.consultations.add(consultation);
    }
    
    public void removeConsultation(Consultation consultation){
        this.consultations.remove(consultation);
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", mail=" + mail + ", motDePasse=" + motDePasse + ", tel=" + tel + ", adressePostale=" + adressePostale + ", dateNaissance=" + dateNaissance + ", disponibilite=" + disponibilite + ", nombreConsultations=" + nombreConsultations + ", consultations=" + consultations + '}';
    }
    
}
