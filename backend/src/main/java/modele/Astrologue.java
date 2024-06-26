/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.Entity;

@Entity
public class Astrologue extends Medium {
    
    private String formation;
    private Integer promotion;

    public Astrologue() {
    }

    public Astrologue(String formation, Integer promotion, String denomination, Boolean genre, String presentation) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }

    public String getFormation() {
        return formation;
    }

    public Integer getPromotion() {
        return promotion;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return super.toString() + " Astrologue{" + "formation=" + formation + ", promotion=" + promotion + '}';
    }
    
    
}
