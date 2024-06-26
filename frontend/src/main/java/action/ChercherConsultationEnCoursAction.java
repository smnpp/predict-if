/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Consultation;
import service.Service;

/**
 *
 * @author sperret
 */
public class ChercherConsultationEnCoursAction extends Action {
    public ChercherConsultationEnCoursAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ChercherConsultationEnCoursAction : début");
        
        HttpSession session = request.getSession(true);
        Long id = (Long) session.getAttribute("idEmploye");
        Consultation consultation = service.rechercherConsultationEnCoursEmploye(id);
        if (consultation != null){
            request.setAttribute("consultation", consultation);
            System.out.println("\u001b[32m" + "[TEST] Execution de ChercherConsultationEnCoursAction : réussie");
        }
        else {
            request.setAttribute("consultation", null);
            System.out.println("[TEST] Execution de ChercherConsultationEnCoursAction : échouée");
        }
        
        System.out.println("[TEST] Execution de ChercherConsultationEnCoursAction : fin");
    }
    
}
