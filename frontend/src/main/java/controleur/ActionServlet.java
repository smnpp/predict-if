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
                    System.out.println("[TEST] ActionServlet: afficherConsultationEnCoursEmploye : début"); 
                    Action action = new ChercherConsultationEnCoursAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ConsultationSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: afficherConsultationEnCoursEmploye : fin");
                    break;
                }
                case "afficherProfilClient": {
                    System.out.println("[TEST] ActionServlet: afficherProfilClient : début"); 
                    Action action = new ConsulterProfilAstralAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ProfilClientSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: afficherProfilClient : fin"); 
                    break;
                }               
                case "ajouterConsultation": {
                    System.out.println("[TEST] ActionServlet: ajouterConsultation : début"); 
                    Action action = new AjouterConsultationAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: ajouterConsultation : fin");
                    break;
                }
                case "connecterClient": {
                    System.out.println("[TEST] ActionServlet: connecterClient : début"); 
                    Action action = new AuthentifierClientAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ProfilClientSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: connecterClient : fin"); 
                    break;
                }
                case "connecterEmploye": {
                    System.out.println("[TEST] ActionServlet: connecterEmploye : début"); 
                    Action action = new AuthentifierEmployeAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ProfilEmployeSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: connecterEmploye : fin");
                    break;
                }
                case "deconnexion": {
                    System.out.println("[TEST] ActionServlet: deconnexion : début"); 
                    Action action = new DeconnexionAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: deconnexion : fin");
                    break;
                }
                case "etrePret": {
                    System.out.println("[TEST] ActionServlet: etrePret : début"); 
                    Action action = new EtrePretAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: etrePret : fin"); 
                    break;
                }
                case "listerCommentaires": {
                    System.out.println("[TEST] ActionServlet: listerCommentaires : début"); 
                    Action action = new ListerCommentairesAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListStringSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: listerCommentaires : fin");
                    break;
                }
                case "listerConsultations": {
                    System.out.println("[TEST] ActionServlet: listerConsultations : début"); 
                    Action action = new ListerConsultationsAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListConsultationsSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: listerConsultations : fin");
                    break; 
                }
                case "listerMediums": {
                    System.out.println("[TEST] ActionServlet: listerMediums : début"); 
                    Action action = new ListerMediumAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListMediumSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: listerMediums : fin"); 
                    break;
                }  
                case "obtenirPredictions": {
                    System.out.println("[TEST] ActionServlet: obtenirPredictions : début"); 
                    Action action = new ObtenirPredictionsAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListStringSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: obtenirPredictions : fin");
                    break;
                }
                case "obtenirStatistiquesGeo": {
                    System.out.println("[TEST] ActionServlet: obtenirStatistiquesGeo : début"); 
                    Action action = new ObtenirStatistiquesGeoAction(service);
                    action.execute(request);

                    Serialisation serialisation = new CoordonneesGeoSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: obtenirStatistiquesGeo : fin"); 
                    break;
                }
                case "obtenirStatistiquesTop5": {
                    System.out.println("[TEST] ActionServlet: obtenirStatistiquesTop5 : début"); 
                    Action action = new ObtenirStatistiquesTop5Action(service);
                    action.execute(request);

                    Serialisation serialisation = new ListMediumSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: obtenirStatistiquesGeo : fin");
                    break; 
                }
                case "previsualiserConsultationClient": {
                    System.out.println("[TEST] ActionServlet: previsualiserConsultationClient : début"); 
                    Action action = new PrevisualiserConsultationClientAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ListConsultationsSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: previsualiserConsultationClient : fin"); 
                    break;
                }
                case "previsualiserProfilClient": {
                    System.out.println("[TEST] ActionServlet: previsualiserProfilClient : début"); 
                    Action action = new PrevisualiserProfilClientAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ProfilClientSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: previsualiserProfilClient : fin");
                    break; 
                }
                case "register": {
                    System.out.println("[TEST] ActionServlet: register : début"); 
                    Action action = new InscriptionAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: register : fin"); 
                    break;
                }
                case "terminerConsultation": {
                    System.out.println("[TEST] ActionServlet: terminerConsultation : début"); 
                    Action action = new TerminerConsultationAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: terminerConsultation : fin");
                    break;
                }
                case "validerCommentaire": {
                    System.out.println("[TEST] ActionServlet: validerCommentaire : début"); 
                    Action action = new ValiderCommentaireAction(service);
                    action.execute(request);

                    Serialisation serialisation = new ReussiteSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: validerCommentaire : fin");
                    break; 
                }
                case "verifierConnexion": {
                    System.out.println("[TEST] ActionServlet: verifierConnexion : début"); 
                    Action action = new VerifierConnexionAction(service);
                    action.execute(request);

                    Serialisation serialisation = new VerifierConnexionSerialisation(service);
                    serialisation.appliquer(request, response);
                    System.out.println("[TEST] ActionServlet: verifierConnexion : fin"); 
                    break;
                }
                default:
                    System.out.println("\u001b[31m" + "[TEST] Action inconnue: " + todo);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue: " + todo);
                    break;
            }
        } else {
            System.out.println("\u001b[31m" + "[TEST] Paramètre 'todo' manquant");
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
