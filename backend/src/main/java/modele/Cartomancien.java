/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.Entity;

@Entity
public class Cartomancien extends Medium {

    public Cartomancien() {
    }

    public Cartomancien(String denomination, Boolean genre, String presentation) {
        super(denomination, genre, presentation);
    }

    @Override
    public String toString() {
        return super.toString() + " Cartomancien{" + '}';
    }
    
    
}
