/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import modele.Medium;

public class MediumDao {

    public void create(Medium medium) {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }

    public void delete(Medium medium) {
        JpaUtil.obtenirContextePersistance().remove(medium);
    }

    public Medium update(Medium medium) {
        return JpaUtil.obtenirContextePersistance().merge(medium);
    }

    public Medium findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    
    public List<Medium> findAll() {
        String s = "select m from Medium m order by m.denomination asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    
    public Medium findByName(String name) {
        // Récupère le medium qui a la même dénomination
        String s = "select c from Medium c where c.denomination LIKE (:name)";
        TypedQuery<Medium> query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        query.setParameter("name", name);
        List<Medium> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
     }
    
    public List<Medium>findtop5(){
        // Récupère les 5 premiers médiums les plus consultés
        String s="select m from Medium m order by m.nombreConsultations desc";
        TypedQuery query=JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        
        query.setMaxResults(5);
        return query.getResultList();
    }

}



