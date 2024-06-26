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
        System.out.println("[TEST] Execution de PrevisualiserProfilClientAction : début");
        
        HttpSession session = request.getSession(true);
        Long id = (Long) session.getAttribute("idEmploye");
        request.setAttribute("idEmploye", id);
        
        Consultation consultationEnCours = service.rechercherConsultationEnCoursEmploye(id);
        Client client = consultationEnCours.getClient();
        ProfilAstral astral = client.getProfilAstral();

        if (client != null) {           
            request.setAttribute("client", client);
            System.out.println("\u001b[32m" + "[TEST] Execution de PrevisualiserProfilClientAction : ajout client réussi");
            if (astral != null){
                request.setAttribute("astral", astral);
                System.out.println("\u001b[32m" + "[TEST] Execution de PrevisualiserProfilClientAction : ajout astral réussi");
            }   
            else {
                request.setAttribute("astral", null);
                System.out.println("\u001b[31m" + "[TEST] Execution de PrevisualiserProfilClientAction : ajout astral échoué");
            }
        }
        else {
            request.setAttribute("client", null);
            request.setAttribute("astral", null);
            System.out.println("\u001b[31m" + "[TEST] Execution de PrevisualiserProfilClientAction : échouée");
        }
        
        System.out.println("[TEST] Execution de PrevisualiserProfilClientAction terminée");
    }
    
}
