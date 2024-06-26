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
public class ConsulterProfilAstralAction extends Action {

    public ConsulterProfilAstralAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ConsulterProfilAstralAction : début");
        
        HttpSession session = request.getSession(true);
        Long idClient = (Long) session.getAttribute("idClient");

        request.setAttribute("idClient", idClient);
        Client client = service.rechercherClientParId(idClient);
        ProfilAstral astral = client.getProfilAstral();
        
        if (client != null) {           
            request.setAttribute("client", client);
            
            if (astral != null){
                request.setAttribute("astral", astral);
            }   
            else {
                request.setAttribute("astral", null);
            }
            System.out.println("\u001b[32m" + "[TEST] Execution de ConsulterProfilAstralAction : réussie");
        }
        else {
            request.setAttribute("client", null);
            request.setAttribute("astral", null);
            System.out.println("\u001b[31m" + "[TEST] Execution de ConsulterProfilAstralAction : échouée");
        }
        System.out.println("[TEST] Execution de ConsulterProfilAstralAction : fin");
    }
}
