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
        System.out.println("[TEST] Execution de AjouterConsultationAction");
        Long idClient = (Long)parseLong(request.getParameter("idClient"));
        String nomMedium = request.getParameter("nomMedium");

        System.out.println("Client ID: " + idClient);
        System.out.println("Medium nom: " + nomMedium);

        Client client = service.rechercherClientParId(idClient);
        Medium medium = service.rechercherMediumParNom(nomMedium);
        Consultation consultation = service.ajouterConsultation(client, medium);

        request.setAttribute("consultation", consultation);
        System.out.println("[TEST] Ajout de consultation terminée");
    }
}
