/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import modele.Client;
import modele.Consultation;
import modele.Employe;

public class ConsultationDao {

    public void create(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }

    public void delete(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().remove(consultation);
    }

    public Consultation update(Consultation consultation) {
        return JpaUtil.obtenirContextePersistance().merge(consultation);
    }

    public Consultation findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Consultation.class, id);
    }

    public List<Consultation> findAll() {
        String s = "select c from Consultation c order by c.date asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        return query.getResultList();
    }
    
    public List<String> findAllCommentaires(){
        // Récupère tous les commentaires non vide de toutes les consultations
        String s="select c.commentaire from Consultation c where c.commentaire !='' ";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        return query.getResultList();
        
    }
    
    public Consultation findByEmp(Employe employe){
        // Trouve la consultation effectué par l'employé en paramètre qui n'est pas terminé
        String s = "select c from Consultation c where c.employe = :Emp and c.validation = false";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Consultation.class);
        query.setParameter("Emp", employe);
        List <Consultation> result= query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    public Consultation findByClient(Client client){
        // Trouve la consultation effectué par le client en paramètre qui est en cours
        String s = "select c from Consultation c where c.client = :Client and c.validation = false";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Consultation.class);
        query.setParameter("Client", client);
        List <Consultation> result= query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
}



