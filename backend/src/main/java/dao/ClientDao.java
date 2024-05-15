/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import modele.Client;
import modele.Employe;

/**
 *
 * @author alexandre armbruster, harold martin
 */
public class ClientDao {

    public void create(Client client) {
        JpaUtil.obtenirContextePersistance().persist(client);
    }

    public void delete(Client client) {
        JpaUtil.obtenirContextePersistance().remove(client);
    }

    public Client update(Client client) {
        return JpaUtil.obtenirContextePersistance().merge(client);
    }

    public Client findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }

    public List<Client> findAll() {
        String s = "select c from Client c order by c.nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        return query.getResultList();
    }

    public Client findByMail(String eMail) {
        // Récupère le client ayant cet adresse mail
        String s = "select c from Client c where c.mail = :eMail";
        TypedQuery<Client> query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        query.setParameter("eMail", eMail);
        Client c;
        try {
            c = query.getSingleResult();
        } catch (Exception ex) {
            c = null;
        }
        return c;
    }
    
    public List<String> findAllAddress() {
        // Récupère toutes les adresses de tous les clients
        String s = "select c.adressePostale from Client c";
        TypedQuery<String> query = JpaUtil.obtenirContextePersistance().createQuery(s, String.class);
        return query.getResultList();
    }
    
     public List <Client> findClientsbyIdEmp(Employe employe){
        String s = "select distinct c.client from Consultation c where c.employe = :Emp and c.validation = true";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Client.class);
        query.setParameter("Emp", employe);
        List <Client> result= query.getResultList();
        return result;
    }
    
}


