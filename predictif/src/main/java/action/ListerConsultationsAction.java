package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import service.Service;
import java.util.List;
import modele.Consultation;

public class ListerConsultationsAction extends Action {

    public ListerConsultationsAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ListerConsultationsAction");
        
        HttpSession session = request.getSession(true);
        Long idClient = (Long) session.getAttribute("idClient");

        if (idClient != null) {
            Client client = service.rechercherClientParId(idClient);
            List<Consultation> consultations = client.getConsultations();
            request.setAttribute("consultations", consultations);
            System.out.println("[TEST] Execution de ListerConsultationsAction réussie");
        } else {
            request.setAttribute("consultations", null);
            System.out.println("[TEST] Execution de ListerConsultationsAction échoué ou aucune consultation");
        }
    }
}
