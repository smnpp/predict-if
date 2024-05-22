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
        System.out.println("[TEST] Execution de ListerMediumAction");
        List<Medium> mediums = service.voirMedium();
        request.setAttribute("mediums", mediums);
        System.out.println("[TEST] Execution de ListerMediumAction terminée");
    }
}