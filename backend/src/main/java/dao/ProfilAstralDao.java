/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import modele.ProfilAstral;

public class ProfilAstralDao {

    public void create(ProfilAstral profilAstral) {
        JpaUtil.obtenirContextePersistance().persist(profilAstral);
    }

    public void delete(ProfilAstral profilAstral) {
        JpaUtil.obtenirContextePersistance().remove(profilAstral);
    }

    public ProfilAstral update(ProfilAstral profilAstral) {
        return JpaUtil.obtenirContextePersistance().merge(profilAstral);
    }

    public ProfilAstral findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(ProfilAstral.class, id);
    }

    public List<ProfilAstral> findAll() {
        String s = "select p from ProfilAstral p order by p.signeZodiaque asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, ProfilAstral.class);
        return query.getResultList();
    }
}


