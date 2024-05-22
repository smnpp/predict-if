/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package action;
import java.util.HashSet;
import java.util.Set;
import service.Service;
import modele.Employe;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sperret
 */
public class DeconnexionAction extends Action {

    public DeconnexionAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de DeconnexionAction");   
        HttpSession session = request.getSession(true);
        if (session.getAttribute("idClient") != null || session.getAttribute("idEmploye") != null) {
            session.setAttribute("idClient", null);
            session.setAttribute("idEmploye", null);
            session.invalidate();
            System.out.println("[TEST] Déconnexion réussie");   
        }
        else {
            System.out.println("[TEST] Déjà déconnecté");   
        }
        request.setAttribute("idClient", null);
        request.setAttribute("idEmploye", null);   
    } 
}
