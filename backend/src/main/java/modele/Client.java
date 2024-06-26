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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String genre;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    @Column(unique = true)
    private String tel;    
    private String adressePostale;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @OneToOne
    private ProfilAstral profilAstral;
    @OneToMany(mappedBy="client")
    private List<Consultation> consultations;
    private Boolean enConsultation;

    public Client() {
        this.consultations = new ArrayList<>();
    }
    
    public Client(String nom, String prenom, String genre, String mail, String motDePasse, String tel, String adressePostale, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.tel = tel;
        this.adressePostale = adressePostale;
        this.dateNaissance = dateNaissance;
        this.consultations = new ArrayList<>();
        this.enConsultation = false;
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

    public String getGenre() {
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

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public Boolean getEnConsultation() {
        return enConsultation;
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

    public void setGenre(String genre) {
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

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }

    public void setEnConsultation(Boolean enConsultation) {
        this.enConsultation = enConsultation;
    }

    public void addConsultation(Consultation consultation){
        this.consultations.add(consultation);
    }
    
    public void removeConsultation(Consultation consultation){
        this.consultations.remove(consultation);
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", mail=" + mail + ", motDePasse=" + motDePasse + ", tel=" + tel + ", adressePostale=" + adressePostale + ", dateNaissance=" + dateNaissance + ", profilAstral=" + profilAstral + ", enConsultation=" + enConsultation + '}';
    }

}
