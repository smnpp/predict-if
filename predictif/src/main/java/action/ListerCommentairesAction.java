/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.Consultation;
import modele.Employe;
import service.Service;

/**
 *
 * @author sperret
 */
public class ListerCommentairesAction extends Action {

    public ListerCommentairesAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ListerConsultationsAction");
        
        List<String> commentaires = service.voirCommentaires();
        if (commentaires != null){
            request.setAttribute("liste", commentaires);
        }
        else {
            request.setAttribute("liste", null);
        }
        System.out.println("[TEST] Execution de ListerConsultationsAction terminée");    
        
        
        /*
        HttpSession session = request.getSession(true);
        Long idEmploye = (Long) session.getAttribute("idEmploye");

        if (idEmploye != null) {
            Employe employe = service.rechercherEmployeParId(idEmploye);
            List<Consultation> consultations = employe.getConsultations();
            request.setAttribute("consultations", consultations);
            System.out.println("[TEST] Execution de ListerConsultationsAction réussie");
        } else {
            request.setAttribute("consultations", null);
            System.out.println("[TEST] Execution de ListerConsultationsAction échoué ou aucune consultation");
        }
        */
        
    }
}
