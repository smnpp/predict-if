/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.ProfilAstral;
import service.Service;

/**
 *
 * @author sperret
 */
public class ConsulterProfilAstralConsultation extends Action {

    public ConsulterProfilAstralConsultation(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de AfficherProfilAction");
        
        HttpSession session = request.getSession(true);
        Long id = (Long) session.getAttribute("idClient");
        request.setAttribute("idClient", id);

        Client client = service.rechercherClientParId(id);
        ProfilAstral astral = client.getProfilAstral();

        request.setAttribute("nom", client.getNom());
        request.setAttribute("prenom", client.getPrenom());
        request.setAttribute("animal", astral.getAnimal());
        request.setAttribute("zodiaque", astral.getSigneZodiaque());
        request.setAttribute("couleur", astral.getCouleur());
        request.setAttribute("chinois", astral.getSigneChinois());
    }
}
