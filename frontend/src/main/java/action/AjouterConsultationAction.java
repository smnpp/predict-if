package action;

import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.Medium;
import modele.Consultation;
import service.Service;

public class AjouterConsultationAction extends Action {

    public AjouterConsultationAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de AjouterConsultationAction : début");
        Long idClient = (Long)parseLong(request.getParameter("idClient"));
        String nomMedium = request.getParameter("nomMedium");

        System.out.println("Client ID: " + idClient);
        System.out.println("Medium nom: " + nomMedium);

        Client client = service.rechercherClientParId(idClient);
        Medium medium = service.rechercherMediumParNom(nomMedium);
        Consultation consultation = service.ajouterConsultation(client, medium);

        
        if (consultation != null) {
            request.setAttribute("success", true);
            System.out.println("\u001b[32m" + "[TEST] Execution de AjouterConsultationAction : réussie");
        }
        else {
            request.setAttribute("success", false);
            System.out.println("\u001b[31m" + "[TEST] Execution de AjouterConsultationAction : échoué");
        }
        System.out.println("[TEST] Execution de AjouterConsultationAction : fin");
    }
}
