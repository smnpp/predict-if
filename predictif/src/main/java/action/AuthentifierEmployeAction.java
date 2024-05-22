package action;

import service.Service;
import modele.Employe;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sperret
 */
public class AuthentifierEmployeAction extends Action {

    public AuthentifierEmployeAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de AuthentifierUtilisateurAction");
        String login = request.getParameter("login");
        String password = request.getParameter("password");       
        
        
        Employe employe = new Employe();
        employe = service.authentifierEmploye(login, password);
        
        if (employe != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("idEmploye", employe.getId());
            session.setAttribute("idClient", null);
            
            request.setAttribute("idEmploye", employe.getId());
            request.setAttribute("idClient", null);
            System.out.println("[TEST] Authentification employe réussie");
        }
        else {
            request.setAttribute("idClient", null);
            request.setAttribute("idEmploye", null);
            System.out.println("[TEST] Authentification employe échouée");
        }
    }
}
