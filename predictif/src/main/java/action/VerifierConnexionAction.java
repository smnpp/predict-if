/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Action pour vérifier l'état de la session.
 */
public class VerifierConnexionAction extends Action {

    public VerifierConnexionAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de VerifierConnexionAction");
        HttpSession session = request.getSession(true);
        Long idClient = (Long) session.getAttribute("idClient");
        Long idEmploye = (Long) session.getAttribute("idEmploye");

        if (idClient != null || idEmploye != null) {
            System.out.println("[TEST] Connecté");
            request.setAttribute("idClient", idClient);
            request.setAttribute("idEmploye", idEmploye);
            request.setAttribute("connected", true);
            System.out.println("[TEST] vérification de connexion términée");
        } else {
            System.out.println("[TEST] Aucune connexion");
            request.setAttribute("idClient", null);
            request.setAttribute("idEmploye", null);
            request.setAttribute("connected", false);
            System.out.println("[TEST] vérification de connexion términée");
        }

    }
}
