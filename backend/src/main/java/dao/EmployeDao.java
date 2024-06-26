/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import modele.Employe;

public class EmployeDao {

    public void create(Employe employe) {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }

    public void delete(Employe employe) {
        JpaUtil.obtenirContextePersistance().remove(employe);
    }

    public Employe update(Employe employe) {
        return JpaUtil.obtenirContextePersistance().merge(employe);
    }

    public Employe findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Employe.class, id);
    }

    public List<Employe> findAll() {
        String s = "select e from Employe e order by e.nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        return query.getResultList();
    }

    public Employe findMail(String eMail) {
        // Récupère l'employé ayant cet adresse mail
        String s = "select e from Employe e where e.mail = :eMail";
        TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        query.setParameter("eMail", eMail);
        Employe c;
        try {
            c = query.getSingleResult();
        } catch (Exception ex) {
            c = null;
        }
        return c;
    }
    
    public Employe findTel(String tel) {
        // Récupère l'employé ayant ce numéro de téléphone
        String s = "select e from Employe e where e.tel = :tel";
        TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        query.setParameter("tel", tel);
        Employe c;
        try {
            c = query.getSingleResult();
        } catch (Exception ex) {
            c = null;
        }
        return c;
    }
    
    public Employe findDisponibleByGenre(Boolean genre) {
        // Cherche l'employé avec le moins de consultation et qui est en respect avoir le genre
        String s = "select e from Employe e where e.genre = :genre and e.disponibilite = true order by e.nombreConsultations asc";
        TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        query.setParameter("genre", genre);
        query.setMaxResults(1);
        Employe e;
        try {
            e = query.getSingleResult();
        } catch (Exception ex) {
            e = null;
        }
        return e;
    }
}


