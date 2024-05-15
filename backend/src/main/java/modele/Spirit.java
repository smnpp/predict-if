/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author alexandre armbruster, harold martin
 */
@Entity
public class Spirit extends Medium {
    
    private List<String> support;

    public Spirit() {
        this.support = new ArrayList<>();
    }

    public Spirit(List<String> support, String denomination, Boolean genre, String presentation) {
        super(denomination, genre, presentation);
        this.support = support;
    }

    public void setSupport(List<String> support) {
        this.support = support;
    }

    public List<String> getSupport() {
        return support;
    }

    @Override
    public String toString() {
        return super.toString() + " Spirit{" + "support=" + support + '}';
    }
    
}
