/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.Consultation;
import modele.Employe;
import modele.ProfilAstral;
import service.Service;

/**
 *
 * @author sperret
 */
public class PrevisualiserProfilClientAction extends Action {
    
     public PrevisualiserProfilClientAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de PrevisualiserProfilClientAction");
        
        HttpSession session = request.getSession(true);
        Long id = (Long) session.getAttribute("idEmploye");
        request.setAttribute("idEmploye", id);
        
        Consultation consultationEnCours = service.rechercherConsultationEnCoursEmploye(id);
        Client client = consultationEnCours.getClient();
        ProfilAstral astral = client.getProfilAstral();
        
        request.setAttribute("client", client); 
        request.setAttribute("astral", astral);
        
        System.out.println("[TEST] Execution de PrevisualiserProfilClientAction terminée");
    }
    
}
