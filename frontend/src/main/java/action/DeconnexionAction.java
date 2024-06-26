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
        System.out.println("[TEST] Execution de DeconnexionAction : début");
        HttpSession session = request.getSession(false); // Get session if exists, do not create a new one
        if (session != null) {
            try {
                if (session.getAttribute("idClient") != null || session.getAttribute("idEmploye") != null) {
                    session.setAttribute("idClient", null);
                    session.setAttribute("idClient", null);
                    session.invalidate(); // Invalidate the session to remove all attributes
                    request.setAttribute("success", true);
                    System.out.println("\u001b[32m" + "[TEST] Execution de DeconnexionAction : déconnecté");
                } else {
                    request.setAttribute("success", true);
                    System.out.println("\u001b[32m" + "[TEST] Execution de DeconnexionAction : déjà deconnecté");
                }
            } catch (Exception e) {
                request.setAttribute("success", false);
                System.out.println("\u001b[31m" + "[TEST] Execution de DeconnexionAction : erreur lors de la déconnexion : " + e.getMessage());
            }
        } else {
            request.setAttribute("success", true);
            System.out.println("\u001b[32m" + "[TEST] Execution de DeconnexionAction : aucune session connectée");
        }
        System.out.println("[TEST] Execution de DeconnexionAction : fin");
    }
}
