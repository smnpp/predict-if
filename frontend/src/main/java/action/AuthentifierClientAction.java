package action;

import service.Service;
import modele.Client;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.ProfilAstral;

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
        System.out.println("[TEST] Execution de AuthentifierUtilisateurAction : début");
        String login = request.getParameter("login");
        String password = request.getParameter("password");       
        
        
        Client client = new Client();
        client = service.authentifierClient(login, password);
        ProfilAstral astral = client.getProfilAstral();
        
        if (client != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("idClient", client.getId());
            session.setAttribute("idEmploye", null);
            
            request.setAttribute("client", client);
            request.setAttribute("employe", null);
            
            if (astral != null){
                request.setAttribute("astral", astral);
            }   
            else {
                request.setAttribute("astral", null);
            }
            System.out.println("\u001b[32m" +"[TEST] Execution de AuthentifierUtilisateurAction : réussie");
        }
        else {
            request.setAttribute("client", null);
            request.setAttribute("astral", null);
            request.setAttribute("employe", null);
            System.out.println("\u001b[31m" + "[TEST] Execution de AuthentifierUtilisateurAction : échouée");
        }
        System.out.println("[TEST] Execution de AuthentifierUtilisateurAction : fin");
    } 
}
