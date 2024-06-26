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
import modele.Employe;
import service.Service;

/**
 *
 * @author sperret
 */
public class ListerCommentairesAction extends Action {

    public ListerCommentairesAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ListerCommentairesAction : début");
        
        List<String> commentaires = service.voirCommentaires();
        if (commentaires != null){
            request.setAttribute("liste", commentaires);
            System.out.println("\u001b[32m" + "[TEST] Execution de ListerCommentairesAction : réussie");
        }
        else {
            request.setAttribute("liste", null);
            System.out.println("\u001b[31m" + "[TEST] Execution de ListerCommentairesAction : null");
        }
        System.out.println("[TEST] Execution de ListerCommentairesAction : fin");   
    }
}
