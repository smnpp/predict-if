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
import service.Service;

/**
 *
 * @author sperret
 */
public class PrevisualiserConsultationClientAction extends Action {

    public PrevisualiserConsultationClientAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de PrevisualiserConsultationClientAction");
        
        HttpSession session = request.getSession(true);
        Long idEmploye = (Long) session.getAttribute("idEmploye");
        
        request.setAttribute("idEploye", idEmploye);
        
        Consultation consultationEnCours = service.rechercherConsultationEnCoursEmploye(idEmploye);
        Client client = consultationEnCours.getClient();
        request.setAttribute("idClient", client.getId());

        if (client != null) {      
            List<Consultation> consultations = client.getConsultations();
            request.setAttribute("consultations", consultations);
            System.out.println("[TEST] Execution de PrevisualiserConsultationClientAction réussie");
        } else {
            request.setAttribute("consultations", null);
            System.out.println("[TEST] Execution de PrevisualiserConsultationClientAction échoué ou aucune consultation");
        }
    }
    
}
