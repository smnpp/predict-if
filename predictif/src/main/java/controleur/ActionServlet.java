package controleur;

import action.PrevisualiserConsultationClientAction;
import action.Action;
import action.AfficherConsultationEnCoursAction;
import action.ConsulterProfilAstralConsultation;
import action.AjouterConsultationAction;
import action.AuthentifierClientAction;
import action.AuthentifierEmployeAction;
import action.ConsulterTop5MediumAction;
import action.ListerMediumAction;
import action.VerifierConnexionAction;
import action.DeconnexionAction;
import action.InscriptionAction;
import action.ListerCommentairesAction;
import action.ListerConsultationsAction;
import action.PrevisualiserProfilClientAction;

import dao.JpaUtil;
import serialisation.Serialisation;
import serialisation.ConsulterProfilAstralSerialisation;
import serialisation.InscriptionSerialisation;
import serialisation.ListerMediumSerialisation;
import serialisation.ProfilClientSerialisation;
import serialisation.ProfilEmployeSerialisation;
import serialisation.VerifierConnexionSerialisation;
import serialisation.DeconnexionSerialisation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import serialisation.AfficherConsultationEnCoursSerialisation;
import serialisation.AjouterConsultationSerialisation;
import serialisation.ListerCommentairesSerialisation;
import serialisation.ListerConsultationsSerialisation;
import serialisation.ListerTop5MediumSerialisation;
import serialisation.PrevisualiserConsultationClientSerialisation;
import serialisation.PrevisualiserProfilClientSerialisation;
import service.Service;

@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[TEST] Appel de l'ActionServlet");
        Service service = new Service();

        String todo = request.getParameter("todo");
        System.out.println("[TEST] Parametre todo : " + todo);

        if (todo != null) {
            switch (todo) {
                case "connecterEmploye": {
                    System.out.println("[TEST] ActionServlet: connecterEmploye");
                    Action action = new AuthentifierEmployeAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ProfilEmployeSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "connecterClient": {
                    System.out.println("[TEST] ActionServlet: connecterClient");
                    Action action = new AuthentifierClientAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ProfilClientSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "register": {
                    System.out.println("[TEST] ActionServlet: register");
                    Action action = new InscriptionAction(service);
                    action.execute(request);

                    Serialisation serialisation = new InscriptionSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "verifierConnexion": {
                    System.out.println("[TEST] ActionServlet: verifierConnexion");
                    Action action = new VerifierConnexionAction(service);
                    action.execute(request);

                    Serialisation serialisation = new VerifierConnexionSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "afficherProfilClient": {
                    System.out.println("[TEST] ActionServlet: afficherProfilClient");
                    Action action = new ConsulterProfilAstralConsultation(service);
                    action.execute(request);

                    Serialisation serialisation = new ConsulterProfilAstralSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "deconnexion": {
                    System.out.println("[TEST] ActionServlet: deconnexion");
                    Action action = new DeconnexionAction(service);
                    action.execute(request);

                    Serialisation serialisation = new DeconnexionSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "listerMediums": {
                    System.out.println("[TEST] ActionServlet: listerMediums");
                    Action action = new ListerMediumAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListerMediumSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "ajouterConsultation": {
                    System.out.println("[TEST] ActionServlet: ajouterConsultation");
                    Action action = new AjouterConsultationAction(service);
                    action.execute(request);

                    Serialisation serialisation = new AjouterConsultationSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "listerConsultations": {
                    System.out.println("[TEST] ActionServlet: listerConsultations");
                    Action action = new ListerConsultationsAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListerConsultationsSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "listerTopMedium": {
                    System.out.println("[TEST] ActionServlet: listerConsultations");
                    Action action = new ConsulterTop5MediumAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListerTop5MediumSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "afficherConsultationEnCoursEmploye": {
                    System.out.println("[TEST] ActionServlet: listerConsultations");
                    Action action = new AfficherConsultationEnCoursAction(service);
                    action.execute(request);

                    Serialisation serialisation = new AfficherConsultationEnCoursSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "previsualiserProfilClient": {
                    System.out.println("[TEST] ActionServlet: previsualiserProfilClient");
                    Action action = new PrevisualiserProfilClientAction(service);
                    action.execute(request);

                    Serialisation serialisation = new PrevisualiserProfilClientSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "previsualiserConsultationClient": {
                    System.out.println("[TEST] ActionServlet: previsualiserProfilClient");
                    Action action = new PrevisualiserConsultationClientAction(service);
                    action.execute(request);

                    Serialisation serialisation = new PrevisualiserConsultationClientSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "listerCommentaires": {
                    System.out.println("[TEST] ActionServlet: listerConsultations");
                    Action action = new ListerCommentairesAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListerCommentairesSerialisation(service);
                    serialisation.appliquer(request, response);
                }

                default:
                    System.out.println("[TEST] Action inconnue: " + todo);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue: " + todo);
                    break;
            }
        } else {
            System.out.println("[TEST] Paramètre 'todo' manquant");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètre 'todo' manquant");
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        JpaUtil.fermerFabriquePersistance();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
