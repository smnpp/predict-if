/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Medium {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String denomination;
    private Boolean genre; // Homme : 0/False, Femme : 1/True
    private String presentation;
    private Integer nombreConsultations;
    @OneToMany(mappedBy="medium")
    private List<Consultation> consultations;


    public Medium() {
        this.consultations = new ArrayList<>();
    }

    public Medium(String denomination, Boolean genre, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
        this.consultations = new ArrayList<>();
        this.nombreConsultations = 0;
    }

    public Long getId() {
        return id;
    }

    public String getDenomination() {
        return denomination;
    }

    public Boolean getGenre() {
        return genre;
    }

    public String getPresentation() {
        return presentation;
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

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setGenre(Boolean genre) {
        this.genre = genre;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
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
        return "Medium{" + "id=" + id + ", denomination=" + denomination + ", genre=" + genre + ", presentation=" + presentation + ", nombreConsultations=" + nombreConsultations + ", consultations=" + consultations + '}';
    }
    
    
    
}
