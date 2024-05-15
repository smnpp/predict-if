package action;

import service.Service;
import modele.Employe;
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
            request.setAttribute("employe", employe);
            System.out.println("[TEST] Employé authentifier");
        }
        else {
            request.setAttribute("employe", null);
            System.out.println("[TEST] Employé échouée");
        }
        
        
    }
    
}
