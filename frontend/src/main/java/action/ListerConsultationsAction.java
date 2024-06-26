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
        System.out.println("[TEST] Execution de ListerConsultationsAction : début");
        
        HttpSession session = request.getSession(true);
        Long idClient = (Long) session.getAttribute("idClient");

        if (idClient != null) {
            Client client = service.rechercherClientParId(idClient);
            List<Consultation> consultations = client.getConsultations();
            request.setAttribute("consultations", consultations);
            System.out.println("\u001b[32m" + "[TEST] Execution de ListerConsultationsAction : réussie");
        } else {
            request.setAttribute("consultations", null);
            System.out.println("\u001b[31m" + "[TEST] Execution de ListerConsultationsAction : null");
        }
        System.out.println("[TEST] Execution de ListerConsultationsAction : fin");
    }
}
