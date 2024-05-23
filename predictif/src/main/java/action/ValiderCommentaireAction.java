/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.Consultation;
import modele.ProfilAstral;
import service.Service;

/**
 *
 * @author sperret
 */
public class ValiderCommentaireAction extends Action {
    
     public ValiderCommentaireAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ValiderCommentaireAction");
        
        String commentaire = request.getParameter("commentaire");
        Long idConsultation = (Long)parseLong(request.getParameter("idConsultation"));
        
        Consultation consultation = service.rechercherConsultationParId(idConsultation);
        
        Boolean success = service.validerCommentaire(consultation, commentaire);
        
        if (success) {
            request.setAttribute("success", true);
        }
        else {
            request.setAttribute("success", false);
        }
        
        
        System.out.println("[TEST] Execution de ValiderCommentaireAction terminée");
    }
}
