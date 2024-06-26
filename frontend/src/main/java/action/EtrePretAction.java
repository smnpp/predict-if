/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;
import modele.Consultation;
import service.Service;

/**
 *
 * @author sperret
 */
public class EtrePretAction extends Action {
    
     public EtrePretAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de EtrePretAction : début");
        
        Long idConsultation = (Long)parseLong(request.getParameter("idConsultation"));  
        Consultation consultation = service.rechercherConsultationParId(idConsultation);
        Boolean success = service.etrePret(consultation);
        
        if (success) {
            request.setAttribute("success", true);
            System.out.println("\u001b[32m" + "[TEST] Execution de EtrePretAction : réussie");
        }
        else {
            request.setAttribute("success", false);
            System.out.println("\u001b[31m" + "[TEST] Execution de EtrePretAction : échouée");
        }
        System.out.println("[TEST] Execution de EtrePretAction : fin");
    }
}
