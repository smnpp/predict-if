package action;

import service.Service;
import modele.Client;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sperret
 */
public class AuthentifierClientAction extends Action {

    public AuthentifierClientAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de AuthentifierUtilisateurAction");
        String login = request.getParameter("login");
        String password = request.getParameter("password");       
        
        
        Client client = new Client();
        client = service.authentifierClient(login, password);
        
        if (client != null) {
            request.setAttribute("client", client);
            System.out.println("[TEST] Utilisateur authentifier");
        }
        else {
            request.setAttribute("utilisateur", null);
            System.out.println("[TEST] Utilisateur échouée");
        }
        
        
    }
    
}
