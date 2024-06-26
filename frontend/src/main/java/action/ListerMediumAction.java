package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modele.Medium;
import service.Service;

/**
 * Classe ListerMediumAction
 */
public class ListerMediumAction extends Action {
    public ListerMediumAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ListerMediumAction : début");
        List<Medium> mediums = service.voirMedium();
        
        if (mediums != null) {
            request.setAttribute("mediums", mediums);
            System.out.println("\u001b[32m" + "[TEST] Execution de ListerMediumAction : réussie");
        }
        else {
            request.setAttribute("mediums", null);
            System.out.println("\u001b[31m" + "[TEST] Execution de ListerMediumAction : null");
        } 
        System.out.println("[TEST] Execution de ListerMediumAction : fin");
    }
}