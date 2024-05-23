package controleur;


import dao.JpaUtil;
import service.Service;

import action.PrevisualiserConsultationClientAction;
import action.Action;
import action.ChercherConsultationEnCoursAction;
import action.ConsulterProfilAstralAction;
import action.AjouterConsultationAction;
import action.AuthentifierClientAction;
import action.AuthentifierEmployeAction;
import action.ObtenirStatistiquesTop5Action;
import action.ListerMediumAction;
import action.VerifierConnexionAction;
import action.DeconnexionAction;
import action.EtrePretAction;
import action.InscriptionAction;
import action.ListerCommentairesAction;
import action.ListerConsultationsAction;
import action.ObtenirPredictionsAction;
import action.ObtenirStatistiquesGeoAction;
import action.PrevisualiserProfilClientAction;
import action.TerminerConsultationAction;
import action.ValiderCommentaireAction;

import serialisation.ConsultationSerialisation;
import serialisation.ListStringSerialisation;
import serialisation.ListConsultationsSerialisation;
import serialisation.ReussiteSerialisation;
import serialisation.Serialisation;
import serialisation.ListMediumSerialisation;
import serialisation.ProfilClientSerialisation;
import serialisation.ProfilEmployeSerialisation;
import serialisation.VerifierConnexionSerialisation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import serialisation.CoordonneesGeoSerialisation;



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
                case "afficherConsultationEnCoursEmploye": {
                    System.out.println("[TEST] ActionServlet: listerConsultations");
                    Action action = new ChercherConsultationEnCoursAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ConsultationSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "afficherProfilClient": {
                    System.out.println("[TEST] ActionServlet: afficherProfilClient");
                    Action action = new ConsulterProfilAstralAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ProfilClientSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }               
                case "ajouterConsultation": {
                    System.out.println("[TEST] ActionServlet: ajouterConsultation");
                    Action action = new AjouterConsultationAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
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
                case "connecterEmploye": {
                    System.out.println("[TEST] ActionServlet: connecterEmploye");
                    Action action = new AuthentifierEmployeAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ProfilEmployeSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "deconnexion": {
                    System.out.println("[TEST] ActionServlet: deconnexion");
                    Action action = new DeconnexionAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "etrePret": {
                    System.out.println("[TEST] ActionServlet: etrePret");
                    Action action = new EtrePretAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "listerCommentaires": {
                    System.out.println("[TEST] ActionServlet: listerConsultations");
                    Action action = new ListerCommentairesAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListStringSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "listerConsultations": {
                    System.out.println("[TEST] ActionServlet: listerConsultations");
                    Action action = new ListerConsultationsAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListConsultationsSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "listerMediums": {
                    System.out.println("[TEST] ActionServlet: listerMediums");
                    Action action = new ListerMediumAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListMediumSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }  
                case "obtenirPredictions": {
                    System.out.println("[TEST] ActionServlet: obtenirPredictions");
                    Action action = new ObtenirPredictionsAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListStringSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "obtenirStatistiquesGeo": {
                    System.out.println("[TEST] ActionServlet: obtenirPredictions");
                    Action action = new ObtenirStatistiquesGeoAction(service);
                    action.execute(request);

                    Serialisation serialisation = new CoordonneesGeoSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "obtenirStatistiquesTop5": {
                    System.out.println("[TEST] ActionServlet: listerConsultations");
                    Action action = new ObtenirStatistiquesTop5Action(service);
                    action.execute(request);

                    Serialisation serialisation = new ListMediumSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "previsualiserConsultationClient": {
                    System.out.println("[TEST] ActionServlet: previsualiserProfilClient");
                    Action action = new PrevisualiserConsultationClientAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListConsultationsSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "previsualiserProfilClient": {
                    System.out.println("[TEST] ActionServlet: previsualiserProfilClient");
                    Action action = new PrevisualiserProfilClientAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ProfilClientSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "register": {
                    System.out.println("[TEST] ActionServlet: register");
                    Action action = new InscriptionAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
                }
                case "terminerConsultation": {
                    System.out.println("[TEST] ActionServlet: terminerConsultation");
                    Action action = new TerminerConsultationAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "validerCommentaire": {
                    System.out.println("[TEST] ActionServlet: previsualiserProfilClient");
                    Action action = new ValiderCommentaireAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                }
                case "verifierConnexion": {
                    System.out.println("[TEST] ActionServlet: verifierConnexion");
                    Action action = new VerifierConnexionAction(service);
                    action.execute(request);

                    Serialisation serialisation = new VerifierConnexionSerialisation(service);
                    serialisation.appliquer(request, response);
                    break;
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
